package com.target.targetcasestudy.presentation.dealsdetail.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.target.targetcasestudy.domain.NetworkResult
import com.target.targetcasestudy.domain.usecases.DetailPageUseCase
import com.target.targetcasestudy.presentation.common.BaseDealsUiModel
import com.target.targetcasestudy.presentation.common.DealsUiState
import com.target.targetcasestudy.presentation.common.DealsUiState.Loading
import com.target.targetcasestudy.presentation.common.DealsUiState.Success
import com.target.targetcasestudy.presentation.dealsdetail.datamapper.DealDetailPageMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject


@HiltViewModel
class DetailPageViewModel @Inject constructor(
    private val useCase: DetailPageUseCase,
    private val dealDetailPageMapper: DealDetailPageMapper
) : ViewModel() {

    var salePrice: String? = null

    private var _dealsPageDataList: MutableStateFlow<DealsUiState<List<BaseDealsUiModel>>> =
        MutableStateFlow(Loading)
    val dealsPageDataList: StateFlow<DealsUiState<List<BaseDealsUiModel>>> =
        _dealsPageDataList

    fun getDealDetails(id: String) {
        viewModelScope.launch {
            try {
                when (val result = useCase.getDealDetails(id)) {
                    is NetworkResult.Success -> _dealsPageDataList.emit(
                        Success(
                            dealDetailPageMapper.mapToDealDetailList(
                                result.data,
                                salePrice
                            )
                        )
                    )

                    is NetworkResult.Error -> _dealsPageDataList.emit(DealsUiState.Fail(result.message))
                    is NetworkResult.Exception -> {
                        if (result.e is UnknownHostException) _dealsPageDataList.emit(
                            DealsUiState.Fail(
                                "check your internet"
                            )
                        )
                        else _dealsPageDataList.emit(DealsUiState.Fail("something unexpected happened"))
                        //send to server logger
                    }
                }
            } catch (e: Exception) {
                _dealsPageDataList.emit(DealsUiState.Fail("something unexpected happened"))
                //send to server logger
            }

        }

    }
}