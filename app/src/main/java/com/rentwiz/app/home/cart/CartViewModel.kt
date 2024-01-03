package com.rentwiz.app.home.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rentwiz.app.network.ApiService
import com.rentwiz.app.network.RxService
import com.rentwiz.app.state.CartState
import com.rentwiz.app.state.ResponseState
import com.rentwiz.app.state.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val apiService: ApiService,
    private val rxService: RxService
): ViewModel() {
    private val _cartState = MutableLiveData<ResponseState<Nothing>>()
    val cartSate: LiveData<ResponseState<Nothing>> = _cartState

    fun getUserDetails() = viewModelScope.launch {
        val user = apiService.getUserDetails()
    }

    fun getDetails() {
        rxService.getDetails()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _cartState.value = UserState.DetailsSuccess(it.body())
            }, {
                print(it)
            })
    }


    override fun onCleared() {
        super.onCleared()
    }
}