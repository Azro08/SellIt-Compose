package com.example.sellit_compose.di

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.sellit_compose.util.AuthManager
import dagger.hilt.EntryPoint
import dagger.hilt.EntryPoints
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

private lateinit var injectedAuthManagerEntryPoint: InjectedAuthManagerEntryPoint

@Composable
fun provideAuthManager(): InjectedAuthManagerEntryPoint {
    if (!::injectedAuthManagerEntryPoint.isInitialized) {
        injectedAuthManagerEntryPoint = EntryPoints.get(
            LocalContext.current.applicationContext,
            InjectedAuthManagerEntryPoint::class.java
        )
    }

    return injectedAuthManagerEntryPoint
}

@EntryPoint
@InstallIn(SingletonComponent::class)
interface InjectedAuthManagerEntryPoint {
    val authManager: AuthManager
}