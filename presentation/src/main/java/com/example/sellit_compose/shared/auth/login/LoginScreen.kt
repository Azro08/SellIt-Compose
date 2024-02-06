package com.example.sellit_compose.shared.auth.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sellit_compose.R
import com.example.sellit_compose.di.provideAuthManager
import com.example.sellit_compose.util.AuthManager
import com.example.sellit_compose.util.Constants
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
    authManager: AuthManager = provideAuthManager().authManager,
    onLoginSuccess: () -> Unit
) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val modifier = Modifier
        .background(Color.White)
        .fillMaxWidth()
        .padding(10.dp)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        TextField(
            value = email.value,
            onValueChange = { email.value = it },
            label = { Text("Email") },
            singleLine = true,
            modifier = modifier
        )

        Spacer(modifier = Modifier.padding(10.dp))

        TextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text("Password") },
            modifier = modifier
        )

        Spacer(modifier = Modifier.padding(10.dp))

        Button(
            onClick = {
                coroutineScope.launch {
                    loginViewModel.login(email.value, password.value)
                    loginViewModel.loggedInState.collect { result ->
                        if (result == Constants.ADMIN || result == Constants.USER) {
                            authManager.saveUer(email.value)
                            authManager.saveRole(result)
                            onLoginSuccess()
                        } else Toast.makeText(context, result, Toast.LENGTH_SHORT).show()
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(68.dp)
                .padding(10.dp),
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.pink))
        ) {
            Text(text = "Login", color = Color.White)
        }

    }
}

