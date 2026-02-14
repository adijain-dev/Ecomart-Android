package com.store.stylish.domain.usecase

import com.store.stylish.domain.model.Product
import com.store.stylish.domain.repository.ProductRepository
import com.store.stylish.domain.util.Result
import javax.inject.Inject

class SearchProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(query: String): Result<List<Product>> {
        return if (query.isBlank()) {
            // If query is empty, return all products
            repository.getProducts()
        } else {
            // Otherwise, search with the query
            repository.searchProducts(query)
        }
    }
}
