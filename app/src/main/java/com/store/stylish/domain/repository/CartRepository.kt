package com.store.stylish.domain.repository

import com.store.stylish.domain.model.CartItem
import com.store.stylish.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    fun getCartItems(): Flow<List<CartItem>>
    suspend fun addToCart(product: Product, quantity: Int = 1)
    suspend fun removeFromCart(productId: Int)
    suspend fun updateQuantity(productId: Int, quantity: Int)
    suspend fun clearCart()
    suspend fun getCartItemCount(): Int
}
