package com.store.stylish.domain.usecase

import com.store.stylish.domain.repository.UserPreferencesRepository
import javax.inject.Inject

class SetUserPreferencesUseCase @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository
) {
    
    suspend fun setFirstTimeLogin(isFirstTime: Boolean) {
        userPreferencesRepository.setFirstTimeLogin(isFirstTime)
    }
    
    suspend fun setLoggedIn(isLoggedIn: Boolean) {
        userPreferencesRepository.setLoggedIn(isLoggedIn)
    }
}
