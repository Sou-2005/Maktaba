package com.ElOuedUniv.maktaba.presentation.book.add

data class AddBookUiState(
    val title: String = "",
    val isbn: String = "",
    val pages: String = "",

    val titleError: String? = null,
    val isbnError: String? = null,
    val pagesError: String? = null,

    val isFormValid: Boolean = false,
    val isSuccess: Boolean = false
)
