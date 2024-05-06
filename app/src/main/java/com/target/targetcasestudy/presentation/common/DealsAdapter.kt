package com.target.targetcasestudy.presentation.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.target.targetcasestudy.presentation.common.adpaterfactory.DealsAdapterFactory
import com.target.targetcasestudy.presentation.common.viewholder.AbstractViewHolder

class DealsAdapter(private val dealsAdapterFactory: DealsAdapterFactory) :
    ListAdapter<BaseDealsUiModel, AbstractViewHolder<*>>(DiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbstractViewHolder<*> {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return dealsAdapterFactory.createViewHolder(view, viewType)
    }

    override fun onBindViewHolder(holder: AbstractViewHolder<*>, position: Int) {
        bind(holder as AbstractViewHolder<BaseDealsUiModel>, getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return currentList[position]?.type(dealsAdapterFactory)!!
    }

    private fun bind(holder: AbstractViewHolder<BaseDealsUiModel>, item: BaseDealsUiModel) {
        holder.bind(item)
    }
}