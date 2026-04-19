package com.ElOuedUniv.maktaba.data.repository

import com.ElOuedUniv.maktaba.data.model.Book
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor() : BookRepository {

    private val _booksList = mutableListOf(
        Book(
            isbn = "9780132350884", 
            title = "Clean Code", 
            nbPages = 464,
            imageUrl = "https://covers.openlibrary.org/b/isbn/9780132350884-L.jpg"
        ),
        Book(
            isbn = "9780135957059", 
            title = "The Pragmatic Programmer", 
            nbPages = 352,
            imageUrl = "https://covers.openlibrary.org/b/isbn/9780135957059-L.jpg"
        ),
        Book(
            isbn = "9780201633610", 
            title = "Design Patterns", 
            nbPages = 395,
            imageUrl = "https://covers.openlibrary.org/b/isbn/9780201633610-L.jpg"
        ),
        Book(
            isbn = "9780134757599", 
            title = "Refactoring", 
            nbPages = 448,
            imageUrl = "https://covers.openlibrary.org/b/isbn/9780134757599-L.jpg"
        ),
        Book(
            isbn = "9781492078005", 
            title = "Head First Design Patterns", 
            nbPages = 672,
            imageUrl = "https://covers.openlibrary.org/b/isbn/9781492078005-L.jpg"
        )
    )

    private val booksFlow = MutableSharedFlow<List<Book>>(replay = 1).apply {
        tryEmit(_booksList.toList())
    }
    
    override fun getAllBooks(): Flow<List<Book>> = flow {
        delay(1000) // Simulate delay
        emitAll(booksFlow)
    }

    override fun getBookByIsbn(isbn: String): Book? {
        return _booksList.find { it.isbn == isbn }
    }

    override fun addBook(book: Book) {
        _booksList.add(book)
        booksFlow.tryEmit(_booksList.toList())
    }
}
