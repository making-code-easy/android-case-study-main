package com.target.targetcasestudy.presentation.common.adpaterfactory

import android.view.View
import com.target.targetcasestudy.presentation.common.viewholder.AbstractViewHolder
import com.target.targetcasestudy.presentation.dealsdetail.ui.uimodels.DealCardUiModel
import com.target.targetcasestudy.presentation.dealsdetail.ui.uimodels.DealInfoUiModel
import com.target.targetcasestudy.presentation.dealsdetail.ui.uimodels.DealSeparatorUiModel
import com.target.targetcasestudy.presentation.dealsdetail.ui.viewholders.DealCardViewHolder
import com.target.targetcasestudy.presentation.dealsdetail.ui.viewholders.DealInfoViewHolder
import com.target.targetcasestudy.presentation.dealsdetail.ui.viewholders.DealSeparatorViewHolder
import com.target.targetcasestudy.presentation.dealslist.listeners.DealListPageListeners
import com.target.targetcasestudy.presentation.dealslist.ui.uimodels.DealsItemUiModel
import com.target.targetcasestudy.presentation.dealslist.ui.viewholders.DealsItemViewHolder

class DealsAdapterFactoryImpl(private val listeners: DealListPageListeners? = null) :
    DealsAdapterFactory {
    override fun type(element: DealsItemUiModel): Int {
        return DealsItemViewHolder.LAYOUT
    }

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
            DealsItemViewHolder.LAYOUT -> DealsItemViewHolder(view, listeners)
            DealCardViewHolder.LAYOUT -> DealCardViewHolder(view)
            DealInfoViewHolder.LAYOUT -> DealInfoViewHolder(view)
            DealSeparatorViewHolder.LAYOUT -> DealSeparatorViewHolder(view)
            else -> {
                throw Exception("no view holder supported")
            }
        }
    }
}