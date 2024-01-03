package com.rentwiz.app.state

import com.rentwiz.app.home.cart.CartDetailsResponse
import com.rentwiz.app.network.EndPoint
import com.rentwiz.app.network.User

sealed class ResponseState<T> {
    data class Success<T>(val data: T) : ResponseState<T>()
    data class Error<T>(val errorMessage: String) : ResponseState<T>()
    data class Loading<T>(val loaderMessage: String) : ResponseState<T>()
}

sealed class CartState : ResponseState<Nothing>() {
    data class DetailsSuccess(val response: List<CartDetailsResponse>) : CartState()
}

sealed class UserState : ResponseState<Nothing>() {
    data class DetailsSuccess(val response: EndPoint?) : UserState()
}