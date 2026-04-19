package com.ElOuedUniv.maktaba.presentation.book.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ElOuedUniv.maktaba.data.model.Book
import com.ElOuedUniv.maktaba.domain.usecase.AddBookUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class AddBookViewModel @Inject constructor(
    private val addBookUseCase: AddBookUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddBookUiState())
    val uiState = _uiState.asStateFlow()

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
                _uiState.update { it.copy(pages = action.pages) }
                validateInputs()
            }
            is AddBookUiAction.OnAddClick -> submitForm()
            is AddBookUiAction.OnCancelClick -> resetState()
        }
    }

    private fun validateInputs() {
        val currentState = _uiState.value

        val titleValid = currentState.title.isNotBlank()

        val isbnValid = currentState.isbn.length == 13 &&
                currentState.isbn.all { it.isDigit() }

        val pagesValid = currentState.pages.toIntOrNull()?.let { it > 0 } == true

        _uiState.update {
            it.copy(
                titleError = if (!titleValid) "Title cannot be empty" else null,
                isbnError = if (!isbnValid) "ISBN must be exactly 13 digits" else null,
                pagesError = if (!pagesValid) "Pages must be a positive number" else null,
                isFormValid = titleValid && isbnValid && pagesValid
            )
        }
    }

    private fun submitForm() {
        validateInputs()
        if (!_uiState.value.isFormValid) return

        viewModelScope.launch {
            val state = _uiState.value
            val newBook = Book(
                id = UUID.randomUUID().toString(),
                title = state.title.trim(),
                isbn = state.isbn.trim(),
                nbPages = state.pages.trim().toInt()
            )
            addBookUseCase(newBook)
            _uiState.update { it.copy(isSuccess = true) }
        }
    }

    private fun resetState() {
        _uiState.update { AddBookUiState() }
    }
}