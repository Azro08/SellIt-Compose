package com.example.sellit_compose.shared.auth

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sellit_compose.R
import com.example.sellit_compose.shared.auth.login.LoginScreen
import com.example.sellit_compose.shared.auth.register.RegisterScreen

@Composable
fun AuthScreen() {
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    val tabTitles = listOf("Login", "Register")
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.sell_it_logo),
            contentDescription = null,
            modifier = Modifier
                .height(240.dp),
            contentScale = ContentScale.FillWidth
        )

        TabRow(
            selectedTabIndex = selectedTabIndex,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[selectedTabIndex])
                        .wrapContentHeight(),
                    color = Color.Black,
                )
            },
        ) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = {
                        selectedTabIndex = index
                    },
                    modifier = Modifier
                        .height(40.dp)
                        .background(Color.White)
                ) {
                    Text(
                        text = title,
                        color = if (selectedTabIndex == index) Color.Black else colorResource(id = R.color.pink),
                        fontSize = 20.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Content
        when (selectedTabIndex) {
            0 -> LoginScreen {
                Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show()
            }

            1 -> RegisterScreen {
                Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
