package com.example.domain.usecase

import com.example.domain.repository.AuthRepository
import com.example.domain.repository.UsersRepository

class DeleteAccountUseCase(
    private val usersRepository: UsersRepository,
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(uid: String): String {
        authRepository.deleteAccount(uid).let {
            return if (it == "Done") {
                usersRepository.deleteAccount(uid)
                usersRepository.deleteUsersProducts(uid)
                "Done"
            } else "Error"
        }
    }


}