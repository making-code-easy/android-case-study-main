package com.target.targetcasestudy.presentation.common.adpaterfactory

import android.view.View
import com.target.targetcasestudy.presentation.common.viewholder.AbstractViewHolder

interface BaseDealsAdapterFactory {
    fun createViewHolder(view: View, viewType: Int): AbstractViewHolder<*>
}