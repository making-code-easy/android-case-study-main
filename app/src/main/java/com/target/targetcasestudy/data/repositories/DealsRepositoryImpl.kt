package com.target.targetcasestudy.data.repositories

import com.target.targetcasestudy.data.datasource.remote.RemoteDataSource
import com.target.targetcasestudy.domain.NetworkResult
import com.target.targetcasestudy.domain.entities.DealItem
import com.target.targetcasestudy.domain.repositoryinterfaces.DealsRepository
import javax.inject.Inject

class DealsRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    DealsRepository {

    override suspend fun getDeals(): NetworkResult<List<DealItem>> {
        return remoteDataSource.getDeals()
    }

    override suspend fun getDealsDetails(id: String): NetworkResult<DealItem> {
        return remoteDataSource.getDealsDetail(id)
    }
}