package com.rentwiz.app.core.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.rentwiz.app.core.base.BuildConfigProvider
import com.rentwiz.app.core.network.CoreInterceptor
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesRetrofit(
        buildConfigProvider: BuildConfigProvider
    ) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(buildConfigProvider.getBaseUrl())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @Named("rx")
    fun providesRxRetrofit(
        buildConfigProvider: BuildConfigProvider,
        okHttpClient: Lazy<OkHttpClient>
    ) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(buildConfigProvider.getBaseUrl())
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .callFactory { okHttpClient.get().newCall(it) }
            .build()
    }

    @Provides
    @Singleton
    fun providesChuckerInterceptor(@ApplicationContext context: Context): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(context)
            .build()
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(
        coreInterceptor: CoreInterceptor,
        chuckerInterceptor: ChuckerInterceptor
    ) : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(coreInterceptor)
            .addInterceptor(chuckerInterceptor)
            .build()
    }
}
