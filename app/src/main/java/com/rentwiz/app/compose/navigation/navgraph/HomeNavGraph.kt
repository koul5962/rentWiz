package com.rentwiz.app.compose.navigation.navgraph

import android.util.Log
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.rentwiz.app.compose.ui.DetailsScreen
import com.rentwiz.app.compose.ui.HomeScreen
import com.rentwiz.app.compose.navigation.DETAILS_ARGUMENT_KEY
import com.rentwiz.app.compose.navigation.DETAILS_ARGUMENT_KEY2
import com.rentwiz.app.compose.navigation.DETAILS_ARGUMENT_KEY3
import com.rentwiz.app.compose.navigation.HOME_ROUTE
import com.rentwiz.app.compose.navigation.Screen

fun NavGraphBuilder.homeNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = Screen.Home.route,
        route = HOME_ROUTE
    ) {
        composable(
            route = Screen.Home.route
        ) {
            HomeScreen(navController)
        }
        composable(
            route = Screen.Details.route,
            arguments = listOf(
                navArgument(DETAILS_ARGUMENT_KEY) {
                    type = NavType.IntType
                },
                navArgument(DETAILS_ARGUMENT_KEY2) {
                    type = NavType.StringType
                },
                navArgument(DETAILS_ARGUMENT_KEY3) {
                    type = NavType.StringType
                }
            )
        ) {
            Log.d("Args", it.arguments?.getInt(DETAILS_ARGUMENT_KEY).toString())
            Log.d("Args", it.arguments?.getString(DETAILS_ARGUMENT_KEY2).toString())
            Log.d("Args", it.arguments?.getString(DETAILS_ARGUMENT_KEY3).toString())
            DetailsScreen(navController)
        }
    }
}