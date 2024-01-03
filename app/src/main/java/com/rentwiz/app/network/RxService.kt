package com.rentwiz.app.network

import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET

interface RxService {

    @GET("user")
    fun getDetails() : Single<Response<EndPoint>>
}