package com.target.targetcasestudy.presentation.common

import com.target.targetcasestudy.presentation.common.adpaterfactory.DealsAdapterFactory

interface BaseDealsUiModel : Visitable<DealsAdapterFactory> {
    fun equalsWith(newData: BaseDealsUiModel): Boolean
}
