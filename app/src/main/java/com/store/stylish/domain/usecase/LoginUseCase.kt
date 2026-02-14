package com.store.stylish.domain.usecase

import com.store.stylish.domain.repository.AuthRepository
import com.store.stylish.domain.util.Result
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository:AuthRepository) {

    suspend operator fun invoke(email:String,password:String):Result<String>{

        return  repository.login(email,password)
    }
}