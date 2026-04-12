package com.ElOuedUniv.maktaba.data.repository

import com.ElOuedUniv.maktaba.data.model.Category

class CategoryRepositoryImpl : CategoryRepository {

    private val categoriesList = listOf<Category>()
    
    override fun getAllCategories(): List<Category> {
        TODO("Not yet implemented")
    }

    override fun getCategoryById(id: String): Category? {
        TODO("Not yet implemented")
    }
}
