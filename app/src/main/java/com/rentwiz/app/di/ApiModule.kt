package com.rentwiz.app.di

import com.rentwiz.app.network.ApiService
import com.rentwiz.app.network.RxService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun provideRetrofit(
        retrofit: Retrofit
    ) : ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideCallRetrofit(
       @Named("rx") retrofit: Retrofit
    ) : RxService {
        return retrofit.create(RxService::class.java)
    }
}