package com.ElOuedUniv.maktaba.presentation.book

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ElOuedUniv.maktaba.data.model.Book
import com.ElOuedUniv.maktaba.domain.usecase.GetBooksUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    private val getBooksUseCase: GetBooksUseCase
) : ViewModel() {

    private val _books = MutableStateFlow<List<Book>>(emptyList())
    val books: StateFlow<List<Book>> = _books.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadBooks()
    }

    fun loadBooks() {
        viewModelScope.launch {
            _isLoading.value = true
                getBooksUseCase().catch {
                    _isLoading.value = false
                }.collect { bookList ->
                    _books.value = bookList
                    _isLoading.value = false
                }

        }
    }

    /**
     * TODO: Exercise 3 - Handle UI Actions
     */
    fun onAction(action: BookUiAction) {
        when (action) {
            BookUiAction.RefreshBooks -> refreshBooks()
            BookUiAction.OnAddBookClick -> {
                // TODO: Set isAddingBook = true in your uiState
            }
            BookUiAction.OnDismissAddBook -> {
                // TODO: Set isAddingBook = false
            }
            is BookUiAction.OnAddBookConfirm -> {
                // TODO: Call AddBookUseCase and hide dialog
            }
        }
    }

    fun refreshBooks() {
        loadBooks()
    }
}

