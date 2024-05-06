package com.target.targetcasestudy.presentation.dealslist.adpaterfactory

import com.target.targetcasestudy.presentation.common.adpaterfactory.BaseDealsAdapterFactory
import com.target.targetcasestudy.presentation.dealslist.ui.uimodels.DealsItemUiModel

interface DealsListAdapterFactory : BaseDealsAdapterFactory {
    fun type(element: DealsItemUiModel): Int
}