package com.rentwiz.app.network


import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("v1/8df43b0b-9130-4d3c-85df-5be9192779fe")
    suspend fun getUserDetails() : Response<User>
}