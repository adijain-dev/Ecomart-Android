package com.store.stylish.domain.usecase

import com.store.stylish.domain.model.Product
import com.store.stylish.domain.repository.ProductRepository
import com.store.stylish.domain.util.Result
import javax.inject.Inject

class GetProductsByCategoryUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(category: String): Result<List<Product>> {
        return repository.getProductsByCategory(category)
    }
}
