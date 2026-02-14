package com.store.stylish.domain.usecase

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.store.stylish.domain.repository.AuthRepository
import com.store.stylish.domain.util.Result
import javax.inject.Inject

class GoogleSignInUseCase @Inject constructor(private val repository: AuthRepository) {

    suspend operator fun invoke(account: GoogleSignInAccount): Result<String> {
        return repository.signInWithGoogle(account)
    }
}
