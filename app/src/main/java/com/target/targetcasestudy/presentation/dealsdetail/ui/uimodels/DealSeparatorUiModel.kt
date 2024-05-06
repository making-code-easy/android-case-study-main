package com.target.targetcasestudy.presentation.dealsdetail.ui.uimodels

import com.target.targetcasestudy.presentation.common.BaseDealsUiModel
import com.target.targetcasestudy.presentation.common.adpaterfactory.DealsAdapterFactory
import com.target.targetcasestudy.presentation.common.utils.toDP

data class DealSeparatorUiModel(val size: Int = 16.toDP) : BaseDealsUiModel {
    override fun equalsWith(newData: BaseDealsUiModel): Boolean {
        return this == newData
    }

    override fun type(type: DealsAdapterFactory): Int {
        return type.type(this)
    }

}
