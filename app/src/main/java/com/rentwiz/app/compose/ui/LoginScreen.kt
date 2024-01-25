package com.rentwiz.app.compose.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.rentwiz.app.compose.navigation.HOME_ROUTE
import com.rentwiz.app.compose.navigation.Screen
import com.rentwiz.app.ui.theme.RentWizTheme


@Composable
fun LoginScreen(
    navController: NavController
) {
    RentWizTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.clickable {
                    navController.navigate(route = Screen.SignUp.route)
                },
                text = "Login"
            )

            Text(
                modifier = Modifier.clickable {
                    navController.navigate(route = HOME_ROUTE) {
                        popUpTo(HOME_ROUTE)
                    }
                },
                text = "Go back"
            )
        }
    }
}