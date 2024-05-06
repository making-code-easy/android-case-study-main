package com.target.targetcasestudy.domain.repositoryinterfaces

import com.target.targetcasestudy.domain.NetworkResult
import com.target.targetcasestudy.domain.entities.DealItem

interface DealsRepository {
    suspend fun getDeals(): NetworkResult<List<DealItem>>
    suspend fun getDealsDetails(id: String): NetworkResult<DealItem>
}