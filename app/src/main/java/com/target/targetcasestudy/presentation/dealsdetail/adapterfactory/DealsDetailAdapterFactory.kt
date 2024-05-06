package com.target.targetcasestudy.presentation.dealsdetail.adapterfactory

import com.target.targetcasestudy.presentation.common.adpaterfactory.BaseDealsAdapterFactory
import com.target.targetcasestudy.presentation.dealsdetail.ui.uimodels.DealCardUiModel
import com.target.targetcasestudy.presentation.dealsdetail.ui.uimodels.DealInfoUiModel
import com.target.targetcasestudy.presentation.dealsdetail.ui.uimodels.DealSeparatorUiModel

interface DealsDetailAdapterFactory : BaseDealsAdapterFactory {
    fun type(element: DealCardUiModel): Int
    fun type(element: DealInfoUiModel): Int
    fun type(element: DealSeparatorUiModel): Int
}