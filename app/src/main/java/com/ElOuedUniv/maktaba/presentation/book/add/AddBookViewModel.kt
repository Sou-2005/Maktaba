package com.ElOuedUniv.maktaba.presentation.book.add

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ElOuedUniv.maktaba.data.model.Book
import com.ElOuedUniv.maktaba.domain.usecase.AddBookUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddBookViewModel @Inject constructor(
    private val addBookUseCase: AddBookUseCase
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(AddBookUiState())
    val uiState = _uiState.asStateFlow()

    private var selectedImageBytes: ByteArray? = null

    fun onAction(action: AddBookUiAction) {
        when (action) {
            is AddBookUiAction.OnTitleChange -> {
                _uiState.update { it.copy(title = action.title) }
                validateInputs()
            }
            is AddBookUiAction.OnIsbnChange -> {
                _uiState.update { it.copy(isbn = action.isbn) }
                validateInputs()
            }
            is AddBookUiAction.OnPagesChange -> {
                _uiState.update { it.copy(nbPages = action.pages) }
                validateInputs()
            }
            is AddBookUiAction.OnImageSelected -> {
                selectedImageBytes = action.imageBytes
                _uiState.update { it.copy(imageUrl = "image_selected") } 
            }
            AddBookUiAction.OnAddClick -> {
                if (_uiState.value.isFormValid) {
                    addBook()
                } else {
                    Log.d("AddBookViewModel", "Form is invalid: ${_uiState.value}")
                }
            }
        }
    }

    private fun validateInputs() {
        val title = _uiState.value.title
        val isbn = _uiState.value.isbn
        val nbPages = _uiState.value.nbPages

        val titleError = if (title.isBlank()) "Title cannot be empty" else null
        // Relaxed validation: 10 or 13 digits
        val isbnError = if (isbn.length !in listOf(10, 13) || isbn.any { !it.isDigit() }) 
            "ISBN must be 10 or 13 digits" else null
            
        val pagesInt = nbPages.toIntOrNull()
        val pagesError = if (pagesInt == null || pagesInt <= 0) "Pages must be a positive number" else null

        _uiState.update { 
            it.copy(
                titleError = titleError,
                isbnError = isbnError,
                nbPagesError = pagesError,
                isFormValid = titleError == null && isbnError == null && pagesError == null
            )
        }
    }

    private fun addBook() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            val currentState = _uiState.value
            val book = Book(
                isbn = currentState.isbn,
                title = currentState.title,
                nbPages = currentState.nbPages.toIntOrNull() ?: 0
            )
            try {
                Log.d("AddBookViewModel", "Starting upload for book: ${book.title}")
                addBookUseCase(book, selectedImageBytes)
                Log.d("AddBookViewModel", "Upload successful")
                _uiState.update { it.copy(isLoading = false, isSuccess = true) }
            } catch (e: Exception) {
                Log.e("AddBookViewModel", "Error adding book", e)
                _uiState.update { it.copy(isLoading = false, errorMessage = "Error: ${e.localizedMessage}") }
            }
        }
    }
}
