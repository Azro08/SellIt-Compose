package com.example.sellit_compose.shared.auth.register

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.model.User
import com.example.sellit_compose.R
import kotlinx.coroutines.launch


@Composable
fun RegisterScreen(
    registerViewModel: RegisterViewModel = hiltViewModel(),
    onRegisterSuccess: () -> Unit
) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }
    val fullName = remember { mutableStateOf("") }
    val phoneNumber = remember { mutableStateOf("") }
    val address = remember { mutableStateOf("") }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val modifier = Modifier
        .background(Color.White)
        .fillMaxWidth()
        .padding(10.dp)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Image(
            painter = painterResource(id = R.drawable.profile_icon),
            contentDescription = null,
            modifier = Modifier
                .size(130.dp)
                .border(
                    color = colorResource(id = R.color.black),
                    width = 2.dp,
                    shape = CircleShape
                )
                .clickable { loadImage() },
            contentScale = ContentScale.Crop,
        )

        Spacer(modifier = Modifier.padding(5.dp))

        TextField(
            value = email.value,
            onValueChange = { email.value = it },
            label = { Text("Email") },
            singleLine = true,
            modifier = modifier
        )

        Spacer(modifier = Modifier.padding(5.dp))

        TextField(
            value = fullName.value,
            onValueChange = { fullName.value = it },
            label = { Text("Full Name") },
            modifier = modifier
        )

        Spacer(modifier = Modifier.padding(5.dp))

        TextField(
            value = address.value,
            onValueChange = { address.value = it },
            label = { Text("Address") },
            modifier = modifier
        )

        Spacer(modifier = Modifier.padding(5.dp))

        TextField(
            value = phoneNumber.value,
            onValueChange = { phoneNumber.value = it },
            label = { Text("Phone Number") },
            modifier = modifier
        )

        Spacer(modifier = Modifier.padding(5.dp))

        TextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text("Password") },
            modifier = modifier
        )

        Spacer(modifier = Modifier.padding(5.dp))

        TextField(
            value = confirmPassword.value,
            onValueChange = { confirmPassword.value = it },
            label = { Text("Confirm Password") },
            modifier = modifier
        )

        Spacer(modifier = Modifier.padding(5.dp))

        Button(
            onClick = {
                if (password.value == confirmPassword.value) {
                    val newUser = User(
                        email = email.value,
                        address = address.value,
                        phoneNumber = phoneNumber.value,
                        role = "user",
                        fullName = fullName.value,
                        imageUrl = "imageUrl"
                    )
                    coroutineScope.launch {
                        registerViewModel.register(newUser, password.value)
                        registerViewModel.registerState.collect { result ->
                            if (result == "Done") {
                                onRegisterSuccess()
                            } else {
                                if (result != "") Toast.makeText(
                                    context,
                                    result,
                                    Toast.LENGTH_SHORT
                                ).show()
                                Log.d("Register", result)
                            }
                        }
                    }

                } else Toast.makeText(context, "Passwords don't match!", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(68.dp)
                .padding(10.dp),
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.pink))
        ) {
            Text(text = "Register", color = Color.White)
        }


    }
}

fun loadImage() {

}
