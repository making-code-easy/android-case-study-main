package com.target.targetcasestudy.presentation.dealslist.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.target.targetcasestudy.domain.NetworkResult
import com.target.targetcasestudy.domain.usecases.DealsListUseCase
import com.target.targetcasestudy.presentation.common.BaseDealsUiModel
import com.target.targetcasestudy.presentation.common.DealsUiState
import com.target.targetcasestudy.presentation.common.DealsUiState.Loading
import com.target.targetcasestudy.presentation.common.DealsUiState.Success
import com.target.targetcasestudy.presentation.dealslist.datamapper.DealListMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

private const val TAG = "DealsListViewModel"

@HiltViewModel
class DealsListViewModel @Inject constructor(
    private val useCase: DealsListUseCase,
    private val dealListMapper: DealListMapper
) : ViewModel() {

    private val _dealsList: MutableStateFlow<DealsUiState<List<BaseDealsUiModel>>> =
        MutableStateFlow(Loading)
    val dealsListData: StateFlow<DealsUiState<List<BaseDealsUiModel>>> = _dealsList

    fun getDeals() {
        viewModelScope.launch {
            try {
                when (val result = useCase.getDeals()) {
                    is NetworkResult.Success -> _dealsList.emit(
                        Success(
                            dealListMapper.mapToDealList(
                                result.data
                            )
                        )
                    )

                    is NetworkResult.Error -> _dealsList.emit(DealsUiState.Fail(result.message))
                    is NetworkResult.Exception -> {
                        if (result.e is UnknownHostException) _dealsList.emit(DealsUiState.Fail("check your internet"))
                        else _dealsList.emit(DealsUiState.Fail("something unexpected happened"))
                        //send to server logger
                    }
                }
            } catch (e: Exception) {
                //send to server logger
                _dealsList.emit(DealsUiState.Fail("something unexpected happened"))
            }

        }

    }
}