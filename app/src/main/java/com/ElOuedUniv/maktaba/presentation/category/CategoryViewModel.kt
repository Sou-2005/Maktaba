package com.ElOuedUniv.maktaba.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ElOuedUniv.maktaba.data.model.Category
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CategoryViewModel : ViewModel() {

    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories: StateFlow<List<Category>> = _categories.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadCategories()
    }

    private fun loadCategories() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                // TODO: Use GetCategoriesUseCase instead of dummy data
                // val categoryList = getCategoriesUseCase()
                // _categories.value = categoryList
                
                // Dummy data for demonstration
                _categories.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun refreshCategories() {
        loadCategories()
    }
}
