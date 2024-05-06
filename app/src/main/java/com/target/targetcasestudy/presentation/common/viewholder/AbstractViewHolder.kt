package com.target.targetcasestudy.presentation.common.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class AbstractViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {
    open fun bind(element: T) {}
}