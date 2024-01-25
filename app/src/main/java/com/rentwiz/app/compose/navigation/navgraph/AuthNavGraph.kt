package com.rentwiz.app.compose.navigation.navgraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.rentwiz.app.compose.ui.HomeScreen
import com.rentwiz.app.compose.navigation.AUTHENTICATION_ROUTE
import com.rentwiz.app.compose.navigation.Screen
import com.rentwiz.app.compose.ui.LoginScreen
import com.rentwiz.app.compose.ui.SignUp

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = Screen.Login.route,
        route = AUTHENTICATION_ROUTE
    ) {
        composable(
            route = Screen.Login.route
        ) {
            LoginScreen(navController)
        }
        composable(
            route = Screen.SignUp.route
        ) {
            SignUp(navController)
        }
    }
}