package com.example.domain.usecase

import com.example.domain.model.User
import com.example.domain.repository.AuthRepository

class RegisterUseCase (
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(user : User, password : String) : String{
        return try {
            authRepository.signup(user.email, password).let { uid->
                user.id = uid
                if (uid.isNotEmpty()) {
                    authRepository.saveUser(user).let {
                        if (it == "Done") "Done" else {
                            authRepository.deleteAccount(uid)
                            it
                        }
                    }
                } else {
                    authRepository.deleteAccount(uid)
                    "Error creating account"
                }
            }

        } catch (e : Exception){
            e.message.toString()
        }
    }

}