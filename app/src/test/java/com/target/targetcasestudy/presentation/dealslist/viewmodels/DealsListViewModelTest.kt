package com.target.targetcasestudy.presentation.dealslist.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.target.targetcasestudy.CoroutineTestDispatchersProvider
import com.target.targetcasestudy.constants.TestConstant.SALE_PRICE
import com.target.targetcasestudy.constants.TestConstant.TEST_ERROR
import com.target.targetcasestudy.domain.NetworkResult
import com.target.targetcasestudy.domain.usecases.DealsListUseCase
import com.target.targetcasestudy.presentation.common.BaseDealsUiModel
import com.target.targetcasestudy.presentation.common.DealsUiState
import com.target.targetcasestudy.presentation.dealslist.datamapper.DealListMapper
import com.target.targetcasestudy.presentation.dealslist.ui.uimodels.DealsItemUiModel
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.net.UnknownHostException


@ExperimentalCoroutinesApi
class DealsListViewModelTest {

    private lateinit var viewModel: DealsListViewModel
    private lateinit var useCase: DealsListUseCase
    private lateinit var dataMapper: DealListMapper
    private lateinit var dispatcher: CoroutineTestDispatchersProvider
    private lateinit var id: String
    private lateinit var dealsPageList: List<BaseDealsUiModel>

    @get:Rule
    var rule = InstantTaskExecutorRule()


    @Before
    fun setUp() {
        useCase = mockk(relaxed = true)
        dataMapper = mockk(relaxed = true)
        dispatcher = CoroutineTestDispatchersProvider
        Dispatchers.setMain(dispatcher.testDispatcher)
        viewModel = DealsListViewModel(useCase, dataMapper)

        dealsPageList = listOf(
            DealsItemUiModel("", SALE_PRICE, "", "", "", "", "")
        )
    }

    @Test
    fun `get deals success`() {
        runTest {
            coEvery { useCase.getDeals() } returns NetworkResult.Success(mockk())
            coEvery { dataMapper.mapToDealList(any()) } returns dealsPageList

            viewModel.getDeals()

            val data = viewModel.dealsListData.value
            assertEquals(
                SALE_PRICE,
                ((data as DealsUiState.Success).data[0] as DealsItemUiModel).salePrice
            )
        }

    }

    @Test
    fun `test get deals fails`() {

        runTest {
            coEvery { useCase.getDeals() } returns  NetworkResult.Error(TEST_ERROR)

            viewModel.getDeals()

            val data = viewModel.dealsListData.value
            assertEquals(
                TEST_ERROR,
                (data as DealsUiState.Fail).message
            )
        }
    }

    @Test
    fun `test get deals exception in response`() {

        runTest {
            coEvery { useCase.getDeals() } returns NetworkResult.Exception(Exception("something unexpected happened"))

            viewModel.getDeals()

            val data = viewModel.dealsListData.value
            assertEquals(
                "something unexpected happened",
                (data as DealsUiState.Fail).message
            )
        }
    }

    @Test
    fun `test get deals  throws exception`() {

        runTest {
            coEvery { useCase.getDeals() } throws Exception("something unexpected happened")

            viewModel.getDeals()

            val data = viewModel.dealsListData.value
            assertEquals(
                "something unexpected happened",
                (data as DealsUiState.Fail).message
            )
        }
    }

    @Test
    fun `test get deals  throws unknown host exception`() {

        runTest {
            coEvery { useCase.getDeals() } returns  NetworkResult.Exception(UnknownHostException())

            viewModel.getDeals()

            val data = viewModel.dealsListData.value
            assertEquals(
                "check your internet",
                (data as DealsUiState.Fail).message
            )
        }
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

}