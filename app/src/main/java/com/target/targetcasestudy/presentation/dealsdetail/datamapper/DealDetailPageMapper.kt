package com.target.targetcasestudy.presentation.dealsdetail.datamapper

import com.target.targetcasestudy.common.CoroutineDispatchers
import com.target.targetcasestudy.domain.entities.DealItem
import com.target.targetcasestudy.presentation.common.BaseDealsUiModel
import com.target.targetcasestudy.presentation.dealsdetail.ui.uimodels.DealCardUiModel
import com.target.targetcasestudy.presentation.dealsdetail.ui.uimodels.DealInfoUiModel
import com.target.targetcasestudy.presentation.dealsdetail.ui.uimodels.DealSeparatorUiModel
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DealDetailPageMapper @Inject constructor(private val dispatchers: CoroutineDispatchers) {
    suspend fun mapToDealDetailList(deal: DealItem, salePrice: String?): List<BaseDealsUiModel> {
        return withContext(dispatchers.default) {
            listOf(
                DealCardUiModel(
                    deal.imageUrl,
                    deal.title,
                    salePrice ?: "",
                    deal.status,
                    deal.regularPrice
                ),
                DealSeparatorUiModel(),
                DealInfoUiModel("Product Details", deal.description)
            )
        }


    }
}