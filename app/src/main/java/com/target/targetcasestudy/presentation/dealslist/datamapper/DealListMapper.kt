package com.target.targetcasestudy.presentation.dealslist.datamapper

import com.target.targetcasestudy.common.CoroutineDispatchers
import com.target.targetcasestudy.domain.entities.DealItem
import com.target.targetcasestudy.presentation.common.BaseDealsUiModel
import com.target.targetcasestudy.presentation.dealslist.ui.uimodels.DealsItemUiModel
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DealListMapper @Inject constructor(private val dispatchers: CoroutineDispatchers) {
    suspend fun mapToDealList(deals: List<DealItem>): List<BaseDealsUiModel> {
        return withContext(dispatchers.default) {
            deals.map { item ->
                DealsItemUiModel(
                    id = item.id,
                    salePrice = item.salePrice,
                    regularPrice = item.regularPrice,
                    status = item.status,
                    title = item.title,
                    imageUrl = item.imageUrl,
                    availability = item.aisle
                )
            }
        }

    }
}