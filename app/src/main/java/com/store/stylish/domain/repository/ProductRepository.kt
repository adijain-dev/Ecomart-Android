package com.store.stylish.domain.repository

import com.store.stylish.domain.model.CategoryItem
import com.store.stylish.domain.model.Product
import com.store.stylish.domain.util.Result

interface ProductRepository {
    suspend fun getProducts(): Result<List<Product>>
    suspend fun searchProducts(query: String): Result<List<Product>>
    suspend fun getCategories(): Result<List<CategoryItem>>
    suspend fun getProductsByCategory(category: String): Result<List<Product>>
}
