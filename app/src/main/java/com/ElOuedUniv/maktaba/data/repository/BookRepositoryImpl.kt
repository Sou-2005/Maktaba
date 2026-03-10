package com.ElOuedUniv.maktaba.data.repository

import com.ElOuedUniv.maktaba.data.model.Book

class BookRepositoryImpl : BookRepository {

    private val booksList = listOf(
        Book(isbn = "9780136083221", title = "Clean Code", nbPages = 431),
        Book(isbn = "9780135957059", title = "The Pragmatic Programmer", nbPages = 352),
        Book(isbn = "0201633612", title = "Design Patterns", nbPages = 395),
        Book(isbn = "9780201485677", title = "Refactoring", nbPages = 464),
        Book(isbn = "9781492078005", title = "Head First Design Patterns", nbPages = 672),
        Book(isbn = "9780262046305", title = "Introduction to Algorithms Cormen", nbPages = 1312),
        Book(isbn = "9781934356364", title = "Learn to Program", nbPages = 230),
        Book(isbn = "9780262518802", title = "Algorithms Unlocked", nbPages = 240),
        Book(isbn = "9780134340012", title = "How to Solve it by Computer", nbPages = 442),
        Book(isbn = "9780735619678", title = "Code Complete", nbPages = 914)
    )
    
    override fun getAllBooks(): List<Book> {
        return booksList
    }

    override fun getBookByIsbn(isbn: String): Book? {
        return booksList.find { it.isbn == isbn }
    }
}

