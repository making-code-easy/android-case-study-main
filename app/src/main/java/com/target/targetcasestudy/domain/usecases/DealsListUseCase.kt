package com.target.targetcasestudy.domain.usecases

import com.target.targetcasestudy.domain.NetworkResult
import com.target.targetcasestudy.domain.entities.DealItem
import com.target.targetcasestudy.domain.repositoryinterfaces.DealsRepository
import javax.inject.Inject

class DealsListUseCase @Inject constructor(private val repository: DealsRepository) {

    suspend fun getDeals(): NetworkResult<List<DealItem>> {
        return repository.getDeals()
    }

}