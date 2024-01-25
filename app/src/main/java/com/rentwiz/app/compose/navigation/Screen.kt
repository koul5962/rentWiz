package com.rentwiz.app.compose.navigation

const val DETAILS_ARGUMENT_KEY = "id"
const val DETAILS_ARGUMENT_KEY2 = "person"
const val DETAILS_ARGUMENT_KEY3 = "data"

const val AUTHENTICATION_ROUTE = "authentication"
const val ROOT_ROUTE = "root"
const val HOME_ROUTE = "home"
sealed class Screen(val route: String) {
    data object Home: Screen(route = "home_screen")
    data object Details: Screen(
        route = "details_screen/{$DETAILS_ARGUMENT_KEY}/{$DETAILS_ARGUMENT_KEY2}?data={$DETAILS_ARGUMENT_KEY3}") {
        fun passId(id: Int) : String {
            return "details_screen/$id"
        }

        fun passIdAndName(id: Int, name: String) : String {
            return "details_screen/$id/$name"
        }

        fun passIdNameAndDataOptional(id: Int, name: String, data: String = "DATA") : String {
            return "details_screen/$id/$name?data=$data"
        }
    }

    data object Login: Screen(route = "login")

    data object SignUp: Screen(route = "sign_up")
}