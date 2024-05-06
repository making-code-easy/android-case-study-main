package com.target.targetcasestudy.presentation.dealsdetail.ui.viewholders

import android.view.View
import androidx.core.content.ContextCompat
import com.target.targetcasestudy.R
import com.target.targetcasestudy.databinding.DealCardItemBinding
import com.target.targetcasestudy.presentation.common.utils.loadImage
import com.target.targetcasestudy.presentation.common.viewholder.AbstractViewHolder
import com.target.targetcasestudy.presentation.dealsdetail.ui.uimodels.DealCardUiModel

class DealCardViewHolder(view: View) : AbstractViewHolder<DealCardUiModel>(view) {

    private val binding = DealCardItemBinding.bind(view)

    companion object {
        val LAYOUT = R.layout.deal_card_item
    }

    override fun bind(element: DealCardUiModel) {
        binding.run {
            dealCardTitle.text = element.title
            setSalePrice(element.salePrice, element.regularPrice)
            dealCardRegularPrice.text = String.format(
                binding.root.context.getString(R.string.regular_price_prefix),
                element.regularPrice
            )
            dealCardStatus.text = element.status
            dealCardImageView.loadImage(element.imageUrl)
        }
    }

    private fun setSalePrice(salePrice: String, regularPrice: String) {
        if (salePrice.isNotEmpty()) {
            binding.dealCardSalePrice.text = salePrice
            binding.dealCardSalePrice.setTextColor(
                ContextCompat.getColor(
                    binding.root.context,
                    R.color.colorPrimaryDark
                )
            )
        } else {
            binding.dealCardSalePrice.text = regularPrice
            binding.dealCardSalePrice.setTextColor(
                ContextCompat.getColor(
                    binding.root.context,
                    R.color.gray_color
                )
            )

        }
    }
}


