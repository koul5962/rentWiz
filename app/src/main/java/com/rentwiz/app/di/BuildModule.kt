package com.rentwiz.app.di

import com.rentwiz.app.buildConfig.BuildConfigImpl
import com.rentwiz.app.core.base.BuildConfigProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BuildModule {

    @Binds
    abstract fun provideBuildConfig(
        buildConfigImpl: BuildConfigImpl
    ) : BuildConfigProvider
}