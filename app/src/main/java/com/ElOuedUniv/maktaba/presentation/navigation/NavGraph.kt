package com.ElOuedUniv.maktaba.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.ElOuedUniv.maktaba.presentation.book.add.AddBookView
import com.ElOuedUniv.maktaba.presentation.book.detail.BookDetailView
import com.ElOuedUniv.maktaba.presentation.book.BookListView
import com.ElOuedUniv.maktaba.presentation.category.CategoryListView
import com.ElOuedUniv.maktaba.presentation.onboarding.OnboardingView

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.BookList.route
    ) {

        composable(Screen.BookList.route) {
            BookListView(
                onBookClick = { bookId ->
                    navController.navigate(Screen.BookDetail.createRoute(bookId))
                },
                onAddClick = {
                    navController.navigate(Screen.AddBook.route)
                },
                onCategoryClick = {
                    navController.navigate(Screen.Category.route)
                }
            )
        }

        composable(
            route = Screen.BookDetail.route,
            arguments = listOf(navArgument("bookId") { type = NavType.StringType })
        ) { backStackEntry ->
            val bookId = backStackEntry.arguments?.getString("bookId") ?: ""
            BookDetailView(
                bookId = bookId,
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(Screen.Category.route) {
            CategoryListView(
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(Screen.AddBook.route) {
            AddBookView(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}