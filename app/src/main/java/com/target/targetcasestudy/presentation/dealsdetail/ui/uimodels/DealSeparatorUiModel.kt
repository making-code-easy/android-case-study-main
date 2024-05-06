package com.target.targetcasestudy.presentation.dealsdetail.ui.uimodels

import com.target.targetcasestudy.presentation.common.BaseDealsUiModel
import com.target.targetcasestudy.presentation.common.adpaterfactory.BaseDealsAdapterFactory
import com.target.targetcasestudy.presentation.common.utils.toDP
import com.target.targetcasestudy.presentation.dealsdetail.adapterfactory.DealsDetailAdapterFactory

data class DealSeparatorUiModel(val size: Int = 16.toDP) : BaseDealsUiModel {
    override fun equalsWith(newData: BaseDealsUiModel): Boolean {
        return this == newData
    }

    override fun type(type: BaseDealsAdapterFactory): Int {
        return (type as DealsDetailAdapterFactory).type(this)
    }

}
