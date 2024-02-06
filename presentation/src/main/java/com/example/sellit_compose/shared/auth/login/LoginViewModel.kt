package com.example.sellit_compose.shared.auth.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {

    private val _loggedInState = MutableStateFlow("")
    val loggedInState = _loggedInState

    fun login(email: String, password: String) = viewModelScope.launch {
        try {
            loginUseCase.invoke(email, password).let {
                _loggedInState.value = it
            }
        } catch (e: Exception) {
            _loggedInState.value = e.message.toString()
            Log.d("ResultExcVM", e.message.toString())
        }
    }

}