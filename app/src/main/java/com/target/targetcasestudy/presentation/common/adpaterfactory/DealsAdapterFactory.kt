package com.target.targetcasestudy.presentation.common.adpaterfactory

import android.view.View
import com.target.targetcasestudy.presentation.common.viewholder.AbstractViewHolder
import com.target.targetcasestudy.presentation.dealsdetail.ui.uimodels.DealCardUiModel
import com.target.targetcasestudy.presentation.dealsdetail.ui.uimodels.DealInfoUiModel
import com.target.targetcasestudy.presentation.dealsdetail.ui.uimodels.DealSeparatorUiModel
import com.target.targetcasestudy.presentation.dealslist.ui.uimodels.DealsItemUiModel

interface DealsAdapterFactory {
    fun type(element: DealsItemUiModel): Int
    fun type(element: DealCardUiModel): Int
    fun type(element: DealInfoUiModel): Int
    fun type(element: DealSeparatorUiModel): Int
    fun createViewHolder(view: View, viewType: Int): AbstractViewHolder<*>
}