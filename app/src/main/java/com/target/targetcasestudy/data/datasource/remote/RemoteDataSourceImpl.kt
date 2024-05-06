package com.target.targetcasestudy.data.datasource.remote

import com.target.targetcasestudy.data.datamapper.DealsDataMapper
import com.target.targetcasestudy.data.datasource.remote.services.DealApiKtx
import com.target.targetcasestudy.data.util.NetworkUtil
import com.target.targetcasestudy.domain.NetworkResult
import com.target.targetcasestudy.domain.entities.DealItem
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val dealApiKtx: DealApiKtx,
    private val dealsDataMapper: DealsDataMapper,
    private val networkUtil: NetworkUtil
) :
    RemoteDataSource {
    override suspend fun getDeals(): NetworkResult<List<DealItem>> {
        val result = networkUtil.handleApi { dealApiKtx.retrieveDeals() }
        return when (result) {
            is NetworkResult.Success -> NetworkResult.Success(dealsDataMapper.mapToDealsList(result.data))
            is NetworkResult.Error -> NetworkResult.Error(result.message)
            is NetworkResult.Exception -> NetworkResult.Exception(result.e)
        }

    }

    override suspend fun getDealsDetail(id: String): NetworkResult<DealItem> {

        val result = networkUtil.handleApi { dealApiKtx.retrieveDeal(id) }
        return when (result) {
            is NetworkResult.Success -> NetworkResult.Success(dealsDataMapper.mapToDeal(result.data))
            is NetworkResult.Error -> NetworkResult.Error(result.message)
            is NetworkResult.Exception -> NetworkResult.Exception(result.e)
        }

    }
}

