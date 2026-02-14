package com.store.stylish.domain.usecase

import com.store.stylish.domain.model.CategoryItem
import com.store.stylish.domain.repository.ProductRepository
import com.store.stylish.domain.util.Result
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(): Result<List<CategoryItem>> {
        return repository.getCategories()
    }
}
