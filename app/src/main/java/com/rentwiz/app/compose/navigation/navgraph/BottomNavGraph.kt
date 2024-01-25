package com.rentwiz.app.compose.navigation.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rentwiz.app.compose.navigation.BottomBarScreen
import com.rentwiz.app.compose.ui.DetailsScreen
import com.rentwiz.app.compose.ui.HomeScreen
import com.rentwiz.app.compose.ui.LoginScreen

@Composable
fun BottomNavGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = BottomBarScreen.Home.route)
    {
        composable(
            route = BottomBarScreen.Home.route
        ) {
            HomeScreen(navController = navHostController)
        }

        composable(
            route = BottomBarScreen.Details.route
        ) {
            DetailsScreen(navController = navHostController)
        }

        composable(
            route = BottomBarScreen.Login.route
        ) {
            LoginScreen(navController = navHostController)
        }

    }
}