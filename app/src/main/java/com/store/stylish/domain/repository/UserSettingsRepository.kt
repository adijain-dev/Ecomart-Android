package com.store.stylish.domain.repository

import com.store.stylish.domain.model.UserProfile
import com.store.stylish.domain.util.Result
import kotlinx.coroutines.flow.Flow

interface UserSettingsRepository {
    suspend fun saveUserProfile(userProfile: UserProfile): Result<Unit>
    fun getUserProfile(userId: String): Flow<Result<UserProfile>>
    suspend fun updateUserProfile(userId: String, updates: Map<String, Any>): Result<Unit>
}
