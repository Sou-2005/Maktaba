package com.ElOuedUniv.maktaba.data.repository

import com.ElOuedUniv.maktaba.data.model.Book
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.storage.storage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SupabaseBookRepositoryImpl @Inject constructor(
    private val supabaseClient: SupabaseClient
) : BookRepository {

    override fun getAllBooks(): Flow<List<Book>> = flow {
        val books = supabaseClient.postgrest["books"]
            .select()
            .decodeList<Book>()
        emit(books)
    }

    override suspend fun getBookByIsbn(isbn: String): Book? {
        return try {
            supabaseClient.postgrest["books"]
                .select {
                    filter {
                        eq("isbn", isbn)
                    }
                }
                .decodeSingleOrNull<Book>()
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun addBook(book: Book, imageBytes: ByteArray?) {
        var finalBook = book
        
        if (imageBytes != null) {
            val fileName = "${book.isbn}.jpg"
            val bucket = supabaseClient.storage["book_covers"]
            bucket.upload(fileName, imageBytes) {
                upsert = true
            }
            val publicUrl = bucket.publicUrl(fileName)
            finalBook = book.copy(imageUrl = publicUrl)
        }
        
        supabaseClient.postgrest["books"].insert(finalBook)
    }

    override suspend fun deleteBook(isbn: String) {
        // First delete from database
        supabaseClient.postgrest["books"].delete {
            filter {
                eq("isbn", isbn)
            }
        }
        
        // Optionally delete image from storage if you want to be thorough
        try {
            supabaseClient.storage["book_covers"].delete("${isbn}.jpg")
        } catch (e: Exception) {
            // Ignore if image doesn't exist
        }
    }
}
