package com.target.targetcasestudy.presentation.dealslist.ui.uimodels

import com.target.targetcasestudy.presentation.common.BaseDealsUiModel
import com.target.targetcasestudy.presentation.common.adpaterfactory.BaseDealsAdapterFactory
import com.target.targetcasestudy.presentation.dealslist.adpaterfactory.DealsListAdapterFactory

data class DealsItemUiModel(
    val id: String,
    val salePrice: String,
    val regularPrice: String,
    val status: String,
    val title: String,
    val imageUrl: String,
    val availability: String,
) :
    BaseDealsUiModel {
    override fun equalsWith(newData: BaseDealsUiModel): Boolean {
        return this == newData
    }

    override fun type(type: BaseDealsAdapterFactory): Int {
        return (type as DealsListAdapterFactory).type(this)
    }
}