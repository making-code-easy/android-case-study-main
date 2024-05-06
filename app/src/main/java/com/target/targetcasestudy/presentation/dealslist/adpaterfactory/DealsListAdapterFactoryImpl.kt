package com.target.targetcasestudy.presentation.dealslist.adpaterfactory

import android.view.View
import com.target.targetcasestudy.presentation.common.viewholder.AbstractViewHolder
import com.target.targetcasestudy.presentation.dealslist.listeners.DealListPageListeners
import com.target.targetcasestudy.presentation.dealslist.ui.uimodels.DealsItemUiModel
import com.target.targetcasestudy.presentation.dealslist.ui.viewholders.DealsItemViewHolder

class DealsListAdapterFactoryImpl(private val listeners: DealListPageListeners) :
    DealsListAdapterFactory {
    override fun type(element: DealsItemUiModel): Int {
        return DealsItemViewHolder.LAYOUT
    }

    override fun createViewHolder(view: View, viewType: Int): AbstractViewHolder<*> {
        return when (viewType) {
            DealsItemViewHolder.LAYOUT -> DealsItemViewHolder(view, listeners)
            else -> {
                throw Exception("no view holder supported")
            }
        }
    }
}