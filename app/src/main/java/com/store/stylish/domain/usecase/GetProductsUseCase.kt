package com.store.stylish.domain.usecase

import com.store.stylish.domain.model.Product
import com.store.stylish.domain.repository.ProductRepository
import com.store.stylish.domain.util.Result
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(): Result<List<Product>> {
        return repository.getProducts()
    }
}
