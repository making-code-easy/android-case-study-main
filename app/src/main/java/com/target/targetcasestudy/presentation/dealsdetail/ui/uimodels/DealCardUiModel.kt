package com.target.targetcasestudy.presentation.dealsdetail.ui.uimodels

import com.target.targetcasestudy.presentation.common.BaseDealsUiModel
import com.target.targetcasestudy.presentation.common.adpaterfactory.BaseDealsAdapterFactory
import com.target.targetcasestudy.presentation.dealsdetail.adapterfactory.DealsDetailAdapterFactory

data class DealCardUiModel(
    val imageUrl: String,
    val title: String,
    val salePrice: String,
    val status: String,
    val regularPrice: String
) : BaseDealsUiModel {

    override fun equalsWith(newData: BaseDealsUiModel): Boolean {
        return this == newData
    }

    override fun type(type: BaseDealsAdapterFactory): Int {
        return (type as DealsDetailAdapterFactory).type(this)
    }

}
