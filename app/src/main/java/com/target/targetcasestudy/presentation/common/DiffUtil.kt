package com.target.targetcasestudy.presentation.common

import androidx.recyclerview.widget.DiffUtil

class DiffUtil : DiffUtil.ItemCallback<BaseDealsUiModel>() {
    override fun areItemsTheSame(oldItem: BaseDealsUiModel, newItem: BaseDealsUiModel): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: BaseDealsUiModel, newItem: BaseDealsUiModel): Boolean {
        return oldItem.equalsWith(newItem)
    }

}