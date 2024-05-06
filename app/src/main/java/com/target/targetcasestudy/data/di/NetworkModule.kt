package com.target.targetcasestudy.data.di

import com.target.targetcasestudy.data.datamapper.DealsDataMapper
import com.target.targetcasestudy.data.datasource.remote.RemoteDataSource
import com.target.targetcasestudy.data.datasource.remote.RemoteDataSourceImpl
import com.target.targetcasestudy.data.datasource.remote.services.BASE_URL
import com.target.targetcasestudy.data.datasource.remote.services.DealApiKtx
import com.target.targetcasestudy.data.repositories.DealsRepositoryImpl
import com.target.targetcasestudy.data.util.NetworkUtil
import com.target.targetcasestudy.domain.repositoryinterfaces.DealsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    }

    @Singleton
    @Provides
    fun provideRemoteDataImpl(
        dealApiKtx: DealApiKtx,
        dealsDataMapper: DealsDataMapper,
        networkUtil: NetworkUtil
    ): RemoteDataSource {
        return RemoteDataSourceImpl(dealApiKtx, dealsDataMapper, networkUtil)
    }

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: RemoteDataSource
    ): DealsRepository {
        return DealsRepositoryImpl(remoteDataSource)
    }
}