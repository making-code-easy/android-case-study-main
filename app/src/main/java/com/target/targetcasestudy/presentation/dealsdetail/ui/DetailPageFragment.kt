package com.target.targetcasestudy.presentation.dealsdetail.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.target.targetcasestudy.databinding.FragmentDealItemBinding
import com.target.targetcasestudy.presentation.common.DealConstants.DEAL_ID
import com.target.targetcasestudy.presentation.common.DealConstants.SALE_PRICE
import com.target.targetcasestudy.presentation.common.DealsAdapter
import com.target.targetcasestudy.presentation.common.DealsUiState
import com.target.targetcasestudy.presentation.common.utils.collectLatestLifecycleFlow
import com.target.targetcasestudy.presentation.dealsdetail.adapterfactory.DealsDetailAdapterFactoryImpl
import com.target.targetcasestudy.presentation.dealsdetail.viewmodels.DetailPageViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailPageFragment : Fragment() {

    private lateinit var viewBinding: FragmentDealItemBinding
    private val viewModel: DetailPageViewModel by viewModels()
    private val dealsAdapter: DealsAdapter by lazy {
        DealsAdapter(
            DealsDetailAdapterFactoryImpl()
        )
    }

    private var dealId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentDealItemBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retrieveInitialData()
        getDealData()
        setUpUi()
        collectData()


    }

    private fun retrieveInitialData() {
        dealId = arguments?.getString(DEAL_ID)
        viewModel.salePrice = arguments?.getString(SALE_PRICE)
    }

    private fun setUpUi() {
        viewBinding.recyclerViewDetailsPage.run {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    LinearLayoutManager.VERTICAL
                )
            )
            adapter = dealsAdapter
        }
    }

    private fun getDealData() {
        dealId?.let { viewModel.getDealDetails(it) }
    }

    private fun collectData() {
        collectLatestLifecycleFlow(viewModel.dealsPageDataList) {
            when (it) {
                is DealsUiState.Loading -> {
                    viewBinding.detailPageLoader.visibility = View.VISIBLE
                }

                is DealsUiState.Success -> {
                    viewBinding.detailPageLoader.visibility = View.GONE
                    dealsAdapter.submitList(it.data)
                    viewBinding.buttonView.visibility = View.VISIBLE
                    viewBinding.button.visibility = View.VISIBLE
                }

                is DealsUiState.Fail -> {
                    viewBinding.detailPageLoader.visibility = View.GONE
                    viewBinding.buttonView.visibility = View.GONE
                    viewBinding.button.visibility = View.GONE
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    companion object {
        fun newInstance(extras: Bundle?): Fragment {
            return DetailPageFragment().also {
                it.arguments = extras
            }
        }
    }
}



