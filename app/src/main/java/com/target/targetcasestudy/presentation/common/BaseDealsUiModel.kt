package com.target.targetcasestudy.presentation.common

import com.target.targetcasestudy.presentation.common.adpaterfactory.BaseDealsAdapterFactory

interface BaseDealsUiModel : Visitable<BaseDealsAdapterFactory> {
    fun equalsWith(newData: BaseDealsUiModel): Boolean
}
