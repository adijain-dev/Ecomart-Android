package com.store.stylish.Presentation.Products

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.store.stylish.domain.model.CategoryItem
import com.store.stylish.domain.model.Product
import com.store.stylish.domain.usecase.GetCategoriesUseCase
import com.store.stylish.domain.usecase.GetProductsByCategoryUseCase
import com.store.stylish.domain.usecase.GetProductsUseCase
import com.store.stylish.domain.usecase.SearchProductsUseCase
import com.store.stylish.domain.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val searchProductsUseCase: SearchProductsUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getProductsByCategoryUseCase: GetProductsByCategoryUseCase,
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _productsState = MutableStateFlow<Result<List<Product>>>(Result.Idle)
    val productsState: StateFlow<Result<List<Product>>> = _productsState.asStateFlow()
    
    private val _categoriesState = MutableStateFlow<Result<List<CategoryItem>>>(Result.Idle)
    val categoriesState: StateFlow<Result<List<CategoryItem>>> = _categoriesState.asStateFlow()
    
    private val _selectedCategory = MutableStateFlow<String?>(null)
    val selectedCategory: StateFlow<String?> = _selectedCategory.asStateFlow()
    
    private val _profilePhotoUrl = MutableStateFlow<String?>(null)
    val profilePhotoUrl: StateFlow<String?> = _profilePhotoUrl.asStateFlow()
    
    private var searchJob: Job? = null

    init {
        loadProducts()
        loadCategories()
        loadUserProfilePhoto()
    }
    
    private fun loadUserProfilePhoto() {
        val currentUser = firebaseAuth.currentUser
        _profilePhotoUrl.value = currentUser?.photoUrl?.toString()
    }

    fun loadProducts() {
        Log.d("com.store.ecommerceapplication.Presentation.Products.ProductViewModel", "Loading products")
        _productsState.value = Result.Loading
        viewModelScope.launch {
            try {
                val result = getProductsUseCase()
                Log.d("com.store.ecommerceapplication.Presentation.Products.ProductViewModel", "Products loaded: $result")
                _productsState.value = result
            } catch (e: Exception) {
                Log.e("com.store.ecommerceapplication.Presentation.Products.ProductViewModel", "Error loading products: ${e.message}", e)
                _productsState.value = Result.Failure(e.message ?: "Unknown error")
            }
        }
    }

    fun retryLoading() {
        loadProducts()
    }
    
    fun searchProducts(query: String) {
        // Cancel previous search job
        searchJob?.cancel()
        
        // Clear selected category when searching
        _selectedCategory.value = null
        
        // Debounce search - wait 300ms before executing
        searchJob = viewModelScope.launch {
            delay(300)
            Log.d("ProductViewModel", "Searching for: $query")
            _productsState.value = Result.Loading
            try {
                val result = searchProductsUseCase(query)
                Log.d("ProductViewModel", "Search results: $result")
                _productsState.value = result
            } catch (e: Exception) {
                Log.e("ProductViewModel", "Error searching products: ${e.message}", e)
                _productsState.value = Result.Failure(e.message ?: "Unknown error")
            }
        }
    }
    
    private fun loadCategories() {
        Log.d("ProductViewModel", "Loading categories")
        _categoriesState.value = Result.Loading
        viewModelScope.launch {
            try {
                val result = getCategoriesUseCase()
                Log.d("ProductViewModel", "Categories loaded: $result")
                _categoriesState.value = result
            } catch (e: Exception) {
                Log.e("ProductViewModel", "Error loading categories: ${e.message}", e)
                _categoriesState.value = Result.Failure(e.message ?: "Unknown error")
            }
        }
    }
    
    fun filterByCategory(categorySlug: String) {
        Log.d("ProductViewModel", "Filtering by category: $categorySlug")
        _selectedCategory.value = categorySlug
        _productsState.value = Result.Loading
        viewModelScope.launch {
            try {
                val result = getProductsByCategoryUseCase(categorySlug)
                Log.d("ProductViewModel", "Category products loaded: $result")
                _productsState.value = result
            } catch (e: Exception) {
                Log.e("ProductViewModel", "Error loading category products: ${e.message}", e)
                _productsState.value = Result.Failure(e.message ?: "Unknown error")
            }
        }
    }
    
    fun clearCategoryFilter() {
        Log.d("ProductViewModel", "Clearing category filter")
        _selectedCategory.value = null
        loadProducts()
    }
}
