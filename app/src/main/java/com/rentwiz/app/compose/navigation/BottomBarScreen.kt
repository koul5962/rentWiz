package com.rentwiz.app.compose.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object Home : BottomBarScreen(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )
    data object Details : BottomBarScreen(
        route = "details",
        title = "Details",
        icon = Icons.Default.Person
    )
    data object Login : BottomBarScreen(
        route = "login",
        title = "Login",
        icon = Icons.Default.Lock
    )
}
