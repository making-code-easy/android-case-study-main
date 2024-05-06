package com.target.targetcasestudy.presentation.dealsdetail.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.target.targetcasestudy.CoroutineTestDispatchersProvider
import com.target.targetcasestudy.constants.TestConstant
import com.target.targetcasestudy.constants.TestConstant.DESCRIPTION
import com.target.targetcasestudy.constants.TestConstant.DIVIDER_SIZE
import com.target.targetcasestudy.constants.TestConstant.EXCEPTION
import com.target.targetcasestudy.constants.TestConstant.ID
import com.target.targetcasestudy.constants.TestConstant.IMAGE_URL
import com.target.targetcasestudy.constants.TestConstant.REGULAR_PRICE
import com.target.targetcasestudy.constants.TestConstant.SALE_PRICE
import com.target.targetcasestudy.constants.TestConstant.STATUS
import com.target.targetcasestudy.constants.TestConstant.TEST_ERROR
import com.target.targetcasestudy.constants.TestConstant.TITLE
import com.target.targetcasestudy.domain.NetworkResult
import com.target.targetcasestudy.domain.usecases.DetailPageUseCase
import com.target.targetcasestudy.presentation.common.BaseDealsUiModel
import com.target.targetcasestudy.presentation.common.DealsUiState
import com.target.targetcasestudy.presentation.dealsdetail.datamapper.DealDetailPageMapper
import com.target.targetcasestudy.presentation.dealsdetail.ui.uimodels.DealCardUiModel
import com.target.targetcasestudy.presentation.dealsdetail.ui.uimodels.DealInfoUiModel
import com.target.targetcasestudy.presentation.dealsdetail.ui.uimodels.DealSeparatorUiModel
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
class DetailPageViewModelTest {

    private lateinit var viewModel: DetailPageViewModel
    private lateinit var useCase: DetailPageUseCase
    private lateinit var dataMapper: DealDetailPageMapper
    private lateinit var dispatcher: CoroutineTestDispatchersProvider
    private lateinit var detailsPageList: MutableList<BaseDealsUiModel>

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        useCase = mockk(relaxed = true)
        dataMapper = mockk(relaxed = true)
        dispatcher = CoroutineTestDispatchersProvider
        Dispatchers.setMain(dispatcher.testDispatcher)
        viewModel = DetailPageViewModel(useCase, dataMapper)

        detailsPageList = mutableListOf(
            DealCardUiModel(IMAGE_URL, TITLE, SALE_PRICE, STATUS, REGULAR_PRICE),
            DealSeparatorUiModel(DIVIDER_SIZE),
            DealInfoUiModel(TITLE, DESCRIPTION)
        )
    }

    @Test
    fun `test get details success`() {

        runTest {
            coEvery { useCase.getDealDetails(ID) } returns NetworkResult.Success(mockk())
            coEvery { dataMapper.mapToDealDetailList(any(), any()) } returns detailsPageList

            viewModel.getDealDetails(ID)

            val data = viewModel.dealsPageDataList.value
            assertEquals(
                SALE_PRICE,
                ((data as DealsUiState.Success).data[0] as DealCardUiModel).salePrice
            )

            assertEquals(
                DIVIDER_SIZE,
                (data.data[1] as DealSeparatorUiModel).size
            )

            assertEquals(
                TITLE,
                (data.data[2] as DealInfoUiModel).title
            )
        }
    }

    @Test
    fun `test get details fails`() {

        runTest {
            coEvery { useCase.getDealDetails(ID) } returns NetworkResult.Error(TEST_ERROR)

            viewModel.getDealDetails(ID)

            val data = viewModel.dealsPageDataList.value
            assertEquals(
                TEST_ERROR,
                (data as DealsUiState.Fail).message
            )
        }
    }

    @Test
    fun `test get details exception in response`() {

        runTest {
            coEvery { useCase.getDealDetails(ID) } returns NetworkResult.Exception(Exception("something unexpected happened"))

            viewModel.getDealDetails(ID)

            val data = viewModel.dealsPageDataList.value
            assertEquals(
                "something unexpected happened",
                (data as DealsUiState.Fail).message
            )
        }
    }

    @Test
    fun `test get details  throws exception`() {

        runTest {
            coEvery { useCase.getDealDetails(ID) } throws Exception("something unexpected happened")

            viewModel.getDealDetails(ID)

            val data = viewModel.dealsPageDataList.value
            assertEquals(
                "something unexpected happened",
                (data as DealsUiState.Fail).message
            )
        }
    }

    @Test
    fun `test get deals  throws unknown host exception`() {

        runTest {
            coEvery { useCase.getDealDetails(ID) } returns  NetworkResult.Exception(UnknownHostException())

            viewModel.getDealDetails(ID)

            val data = viewModel.dealsPageDataList.value
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


