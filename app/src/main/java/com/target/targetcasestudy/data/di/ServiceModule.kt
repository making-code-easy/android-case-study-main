package com.target.targetcasestudy.data.di

import com.target.targetcasestudy.data.datasource.remote.services.DealApiKtx
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ServiceModule {

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): DealApiKtx {
        return retrofit.create(DealApiKtx::class.java)
    }
}