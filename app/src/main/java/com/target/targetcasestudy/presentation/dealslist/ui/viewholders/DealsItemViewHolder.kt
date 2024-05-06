package com.target.targetcasestudy.presentation.dealslist.ui.viewholders

import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.core.content.ContextCompat
import com.target.targetcasestudy.R
import com.target.targetcasestudy.databinding.DealListItemBinding
import com.target.targetcasestudy.presentation.common.utils.loadImage
import com.target.targetcasestudy.presentation.common.viewholder.AbstractViewHolder
import com.target.targetcasestudy.presentation.dealslist.listeners.DealListPageListeners
import com.target.targetcasestudy.presentation.dealslist.ui.uimodels.DealsItemUiModel


class DealsItemViewHolder(
    private val view: View,
    private val dealsListPageListeners: DealListPageListeners?
) : AbstractViewHolder<DealsItemUiModel>(view) {

    private val binding = DealListItemBinding.bind(view)

    companion object {
        val LAYOUT = R.layout.deal_list_item
    }

    override fun bind(element: DealsItemUiModel) {
        binding.run {
            setSalePrice(element.salePrice, element.regularPrice)
            dealListRegularPrice.text = String.format(
                binding.root.context.getString(R.string.regular_price_prefix),
                element.regularPrice
            )
            dealListStatus.text = element.status
            dealListItemTitle.text = element.title
            dealListItemAvailability.text = getSpannableString(element.availability)
            dealListItemImageView.loadImage(element.imageUrl)
        }
        binding.root.setOnClickListener {
            dealsListPageListeners?.onClick(element.id, element.salePrice)
        }
    }

    private fun setSalePrice(salePrice: String, regularPrice: String) {
        if (salePrice.isNotEmpty()) {
            binding.dealListSalePrice.text = salePrice
            binding.dealListSalePrice.setTextColor(
                ContextCompat.getColor(
                    binding.root.context,
                    R.color.colorPrimaryDark
                )
            )
        } else {
            binding.dealListSalePrice.text = regularPrice
            binding.dealListSalePrice.setTextColor(
                ContextCompat.getColor(
                    binding.root.context,
                    R.color.gray_color
                )
            )

        }
    }

    private fun getSpannableString(aisle: String): CharSequence {
        val originalText =
            String.format(binding.root.context.getString(R.string.availablity_prefix), aisle)
        val colorGreen = ContextCompat.getColor(view.context, R.color.colorAccent)
        val spannableString = SpannableString(originalText)
        val startIndex = 0
        val endIndex = "In stock".length
        spannableString.setSpan(
            ForegroundColorSpan(colorGreen),
            startIndex,
            endIndex,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return spannableString
    }


}