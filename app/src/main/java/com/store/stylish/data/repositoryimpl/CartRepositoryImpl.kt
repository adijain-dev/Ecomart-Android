package com.store.stylish.data.repositoryimpl

import com.store.stylish.data.local.CartDataStore
import com.store.stylish.domain.model.CartItem
import com.store.stylish.domain.model.Product
import com.store.stylish.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow

class CartRepositoryImpl(
    private val cartDataStore: CartDataStore
) : CartRepository {
    
    override fun getCartItems(): Flow<List<CartItem>> {
        return cartDataStore.cartItems
    }
    
    override suspend fun addToCart(product: Product, quantity: Int) {
        cartDataStore.addToCart(product, quantity)
    }
    
    override suspend fun removeFromCart(productId: Int) {
        cartDataStore.removeFromCart(productId)
    }
    
    override suspend fun updateQuantity(productId: Int, quantity: Int) {
        cartDataStore.updateQuantity(productId, quantity)
    }
    
    override suspend fun clearCart() {
        cartDataStore.clearCart()
    }
    
    override suspend fun getCartItemCount(): Int {
        return cartDataStore.getCartItemCount()
    }
}
