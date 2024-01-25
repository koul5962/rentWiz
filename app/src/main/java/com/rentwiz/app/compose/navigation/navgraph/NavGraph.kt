package com.rentwiz.app.compose.navigation.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.rentwiz.app.compose.navigation.HOME_ROUTE
import com.rentwiz.app.compose.navigation.ROOT_ROUTE
import com.rentwiz.app.compose.navigation.Screen

@Composable
fun SetUpNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = HOME_ROUTE,
        route = ROOT_ROUTE
    ) {
        homeNavGraph(navController)
        authNavGraph(navController)
    }
}