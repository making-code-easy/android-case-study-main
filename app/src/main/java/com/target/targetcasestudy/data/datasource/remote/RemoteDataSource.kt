package com.target.targetcasestudy.data.datasource.remote

import com.target.targetcasestudy.domain.NetworkResult
import com.target.targetcasestudy.domain.entities.DealItem

interface RemoteDataSource {
    suspend fun getDeals(): NetworkResult<List<DealItem>>
    suspend fun getDealsDetail(id: String): NetworkResult<DealItem>
}