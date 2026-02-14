package com.store.stylish.domain.repository

import com.store.stylish.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface WishlistRepository {
    fun getWishlistProducts(): Flow<List<Product>>
    suspend fun addToWishlist(product: Product)
    suspend fun removeFromWishlist(productId: Int)
    suspend fun isInWishlist(productId: Int): Boolean
    suspend fun clearWishlist()
}
