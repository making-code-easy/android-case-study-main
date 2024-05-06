package com.target.targetcasestudy.data.datamapper

import com.target.targetcasestudy.common.CoroutineDispatchers
import com.target.targetcasestudy.data.response.DealsListResponse
import com.target.targetcasestudy.data.response.DetailPageResponse
import com.target.targetcasestudy.domain.entities.DealItem
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DealsDataMapper @Inject constructor(private val dispatchers: CoroutineDispatchers) {

    suspend fun mapToDealsList(res: DealsListResponse): List<DealItem> {
        return withContext(dispatchers.default) {
            res.products.map {
                DealItem(
                    id = it.id,
                    salePrice = it.salePrice.displayString,
                    regularPrice = it.regularPrice.displayString,
                    status = it.fulfillment,
                    title = it.title,
                    description = it.description,
                    aisle = it.aisle,
                    imageUrl = it.imageUrl
                )
            }
        }

    }

    suspend fun mapToDeal(result: DetailPageResponse): DealItem {
        return withContext(dispatchers.default) {
            with(result) {
                DealItem(
                    id = id,
                    regularPrice = regularPrice.displayString,
                    status = fulfillment,
                    title = title,
                    description = description,
                    aisle = aisle,
                    imageUrl = imageUrl
                )
            }
        }
    }

}