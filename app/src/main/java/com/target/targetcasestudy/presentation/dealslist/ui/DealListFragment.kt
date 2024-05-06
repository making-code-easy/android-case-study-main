package com.target.targetcasestudy.presentation.dealslist.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.target.targetcasestudy.databinding.FragmentDealListBinding
import com.target.targetcasestudy.presentation.common.DealConstants.DEAL_ID
import com.target.targetcasestudy.presentation.common.DealConstants.SALE_PRICE
import com.target.targetcasestudy.presentation.common.DealsAdapter
import com.target.targetcasestudy.presentation.common.DealsUiState
import com.target.targetcasestudy.presentation.common.utils.collectLatestLifecycleFlow
import com.target.targetcasestudy.presentation.dealsdetail.ui.DealsDetailActivity
import com.target.targetcasestudy.presentation.dealslist.adpaterfactory.DealsListAdapterFactoryImpl
import com.target.targetcasestudy.presentation.dealslist.listeners.DealListPageListeners
import com.target.targetcasestudy.presentation.dealslist.viewmodels.DealsListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DealListFragment : Fragment() {

    private lateinit var viewBinding: FragmentDealListBinding
    private val viewModel: DealsListViewModel by viewModels()
    private val dealsAdapter: DealsAdapter by lazy {
        DealsAdapter(
            DealsListAdapterFactoryImpl(DealListPageListenersImpl())
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentDealListBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDealsData()
        setUpUi()
        collectData()

    }

    private fun setUpUi() {
        viewBinding.recyclerView.run {
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

    private fun getDealsData() {
        viewModel.getDeals()
    }

    private fun collectData() {
        collectLatestLifecycleFlow(viewModel.dealsListData) {
            when (it) {
                is DealsUiState.Loading -> {
                    viewBinding.dealListPageLoader.visibility = View.VISIBLE
                }

                is DealsUiState.Success -> {
                    viewBinding.dealListPageLoader.visibility = View.GONE
                    dealsAdapter.submitList(it.data)
                }

                is DealsUiState.Fail -> {
                    viewBinding.dealListPageLoader.visibility = View.GONE
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    companion object {
        fun newInstance(): Fragment {
            return DealListFragment()
        }
    }

    inner class DealListPageListenersImpl : DealListPageListeners {
        override fun onClick(id: String, salePrice: String) {
            Intent(context, DealsDetailActivity::class.java).run {
                val bundle = Bundle().also {
                    it.putString(DEAL_ID, id)
                    it.putString(SALE_PRICE, salePrice)
                }
                putExtras(bundle)
                startActivity(this)
            }
        }

    }
}

