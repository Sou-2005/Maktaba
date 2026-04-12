package com.ElOuedUniv.maktaba.presentation.book

/**
 * One-time UI events (System events) for the Book screen.
 * TODO: Student must use this for effects like showing snackbars or navigation.
 */
sealed interface BookUiEvent {
    data class ShowSnackbar(val message: String) : BookUiEvent
    object NavigateToCategories : BookUiEvent
}
