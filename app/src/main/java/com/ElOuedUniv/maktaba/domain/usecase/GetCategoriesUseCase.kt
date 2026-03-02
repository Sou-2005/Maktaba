package com.ElOuedUniv.maktaba.domain.usecase

import com.ElOuedUniv.maktaba.data.model.Category
import com.ElOuedUniv.maktaba.data.repository.CategoryRepository

class GetCategoriesUseCase(
    private val categoryRepository: CategoryRepository
) {
    // ✅ Bonus 3: ترتيب التصنيفات أبجديًا حسب الاسم
    operator fun invoke(): List<Category> {
        return categoryRepository
            .getAllCategories()
            .sortedBy { it.name }
    }
}
