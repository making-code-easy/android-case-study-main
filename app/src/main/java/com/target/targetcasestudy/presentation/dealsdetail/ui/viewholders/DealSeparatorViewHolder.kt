package com.target.targetcasestudy.presentation.dealsdetail.ui.viewholders

import android.view.View
import android.view.ViewGroup
import com.target.targetcasestudy.R
import com.target.targetcasestudy.databinding.DealSepratorItemBinding
import com.target.targetcasestudy.presentation.common.viewholder.AbstractViewHolder
import com.target.targetcasestudy.presentation.dealsdetail.ui.uimodels.DealSeparatorUiModel


class DealSeparatorViewHolder(view: View) : AbstractViewHolder<DealSeparatorUiModel>(view) {

    private val binding = DealSepratorItemBinding.bind(view)

    companion object {
        val LAYOUT = R.layout.deal_seprator_item
    }

    override fun bind(element: DealSeparatorUiModel) {
        val params: ViewGroup.LayoutParams = binding.divider.layoutParams
        params.height = element.size
        binding.root.setLayoutParams(params)

    }
}