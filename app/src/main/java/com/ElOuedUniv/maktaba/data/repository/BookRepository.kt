package com.ElOuedUniv.maktaba.data.repository

import com.ElOuedUniv.maktaba.data.model.Book

class BookRepository {

    private val booksList = listOf(

        Book(
            isbn = "978-0-13-235088-4",
            title = "Clean Code",
            nbPages = 464
        ),

        Book(
            isbn = "978-0-201-61622-4",
            title = "The Pragmatic Programmer",
            nbPages = 352
        ),

        Book(
            isbn = "978-0-13-449416-6",
            title = "Clean Architecture",
            nbPages = 432
        ),

        Book(
            isbn = "978-1-491-94705-0",
            title = "Kotlin in Action",
            nbPages = 360
        ),

        Book(
            isbn = "978-0-596-52068-7",
            title = "Head First Design Patterns",
            nbPages = 694
        ),

        Book(
            isbn = "978-0-262-03384-8",
            title = "Introduction to Algorithms",
            nbPages = 1312
        ),

        Book(
            isbn = "978-0-13-468599-1",
            title = "Effective Java",
            nbPages = 416
        ),

        Book(
            isbn = "978-0-321-35668-0",
            title = "Android Programming: The Big Nerd Ranch Guide",
            nbPages = 560
        ),

        Book(
            isbn = "978-1-59327-992-9",
            title = "Database Design for Mere Mortals",
            nbPages = 696
        ),

        Book(
            isbn = "978-1-118-60118-6",
            title = "Computer Science Distilled",
            nbPages = 176
        ),
        Book(
            isbn = "978-0-201-83595-3",
            title = "Design Patterns: Elements of Reusable Object-Oriented Software",
            nbPages = 395
        ),

        Book(
            isbn = "978-1-491-94711-1",
            title = "Programming Kotlin",
            nbPages = 360
        ),

        Book(
            isbn = "978-0-134-53629-8",
            title = "Refactoring: Improving the Design of Existing Code",
            nbPages = 448
        ),

        Book(
            isbn = "978-0-321-35668-0",
            title = "Android Programming: The Big Nerd Ranch Guide", // لو مش موجود أصلا
            nbPages = 560
        ),

        Book(
            isbn = "978-1-59327-992-9",
            title = "Database Design for Mere Mortals",
            nbPages = 696
        )



    )



    fun getAllBooks(): List<Book> {
        return booksList
    }

    // Bonus: search by title
    fun searchBooksByTitle(query: String): List<Book> {
        return booksList.filter {
            it.title.contains(query, ignoreCase = true)
        }
    }
}