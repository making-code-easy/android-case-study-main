package com.target.targetcasestudy.domain.usecases

import com.target.targetcasestudy.domain.NetworkResult
import com.target.targetcasestudy.domain.entities.DealItem
import com.target.targetcasestudy.domain.repositoryinterfaces.DealsRepository
import javax.inject.Inject

class DetailPageUseCase @Inject constructor(private val repository: DealsRepository) {

    suspend fun getDealDetails(id: String): NetworkResult<DealItem> {
        return repository.getDealsDetails(id)
    }

}