package com.rentwiz.app.compose.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.rentwiz.app.compose.navigation.Screen
import com.rentwiz.app.ui.theme.RentWizTheme

@Composable
fun SignUp(
    navController: NavController
) {
    RentWizTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.clickable {
                    navController.navigate(route = Screen.Login.route) {
                        popUpTo(Screen.SignUp.route) {
                            inclusive = true
                        }
                    }
                                              },
                text = "Sign Up")
        }
    }
}