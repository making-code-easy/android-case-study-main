package com.target.targetcasestudy.presentation.dealsdetail.ui.viewholders

import android.view.View
import com.target.targetcasestudy.R
import com.target.targetcasestudy.databinding.DealInfoItemBinding
import com.target.targetcasestudy.presentation.common.viewholder.AbstractViewHolder
import com.target.targetcasestudy.presentation.dealsdetail.ui.uimodels.DealInfoUiModel

class DealInfoViewHolder(view: View) : AbstractViewHolder<DealInfoUiModel>(view) {

    private val binding = DealInfoItemBinding.bind(view)

    companion object {
        val LAYOUT = R.layout.deal_info_item
    }

    override fun bind(element: DealInfoUiModel) {
        binding.run {
            dealInfoTitle.text = element.title
            dealInfoDescription.text = element.description
        }
    }


}