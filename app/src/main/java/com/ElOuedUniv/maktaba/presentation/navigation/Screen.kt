package com.ElOuedUniv.maktaba.presentation.navigation

sealed class Screen(val route: String) {
    object Onboarding : Screen("onboarding")
    object BookList : Screen("book_list")
    object AddBook : Screen("add_book")
    object Category : Screen("category")

    object BookDetail : Screen("book_detail/{bookId}") {
        fun createRoute(bookId: String) = "book_detail/$bookId"
    }
}