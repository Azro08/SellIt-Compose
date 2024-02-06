package com.azrosk.data.di

import com.example.data.api.UserService
import com.example.data.repository.AuthRepositoryImpl
import com.example.data.repository.UsersRepositoryImpl
import com.example.domain.repository.AuthRepository
import com.example.domain.repository.UsersRepository
import com.example.domain.usecase.DeleteAccountUseCase
import com.example.domain.usecase.LoginUseCase
import com.example.domain.usecase.RegisterUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @Provides
    fun provideAuthRepository(
        firebaseAuth: FirebaseAuth,
        firestore: FirebaseFirestore,
        api: UserService
    ): AuthRepository = AuthRepositoryImpl(
        firebaseAuth, firestore, api
    )

    @Provides
    fun provideRegisterUseCase(repository: AuthRepository): RegisterUseCase =
        RegisterUseCase(repository)

    @Provides
    fun provideLoginUseCase(repository: AuthRepository): LoginUseCase =
        LoginUseCase(repository)

    @Provides
    fun provideUsersRepository(
        firestore: FirebaseFirestore,
        firebaseAuth: FirebaseAuth,
        storage: FirebaseStorage,
        api: UserService
    ): UsersRepository = UsersRepositoryImpl(firestore, firebaseAuth, storage, api)

    @Provides
    fun provideDeleteAccountUseCase(
        usersRepository: UsersRepository,
        authRepository: AuthRepository
    ): DeleteAccountUseCase =
        DeleteAccountUseCase(usersRepository, authRepository)

}