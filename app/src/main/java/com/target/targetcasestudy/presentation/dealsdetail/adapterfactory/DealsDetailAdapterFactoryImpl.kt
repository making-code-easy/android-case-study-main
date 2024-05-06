package com.target.targetcasestudy.presentation.dealsdetail.adapterfactory

import android.view.View
import com.target.targetcasestudy.presentation.common.viewholder.AbstractViewHolder
import com.target.targetcasestudy.presentation.dealsdetail.ui.uimodels.DealCardUiModel
import com.target.targetcasestudy.presentation.dealsdetail.ui.uimodels.DealInfoUiModel
import com.target.targetcasestudy.presentation.dealsdetail.ui.uimodels.DealSeparatorUiModel
import com.target.targetcasestudy.presentation.dealsdetail.ui.viewholders.DealCardViewHolder
import com.target.targetcasestudy.presentation.dealsdetail.ui.viewholders.DealInfoViewHolder
import com.target.targetcasestudy.presentation.dealsdetail.ui.viewholders.DealSeparatorViewHolder

class DealsDetailAdapterFactoryImpl :
    DealsDetailAdapterFactory {

    override fun type(element: DealInfoUiModel): Int {
        return DealInfoViewHolder.LAYOUT
    }

    override fun type(element: DealCardUiModel): Int {
        return DealCardViewHolder.LAYOUT
    }

    override fun type(element: DealSeparatorUiModel): Int {
        return DealSeparatorViewHolder.LAYOUT
    }

    override fun createViewHolder(view: View, viewType: Int): AbstractViewHolder<*> {
        return when (viewType) {
            DealCardViewHolder.LAYOUT -> DealCardViewHolder(view)
            DealInfoViewHolder.LAYOUT -> DealInfoViewHolder(view)
            DealSeparatorViewHolder.LAYOUT -> DealSeparatorViewHolder(view)
            else -> {
                throw Exception("no view holder supported")
            }
        }
    }
}