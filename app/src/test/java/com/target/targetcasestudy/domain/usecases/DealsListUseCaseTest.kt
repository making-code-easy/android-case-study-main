package com.target.targetcasestudy.domain.usecases


import com.target.targetcasestudy.constants.TestConstant.AISLE
import com.target.targetcasestudy.constants.TestConstant.DESCRIPTION
import com.target.targetcasestudy.constants.TestConstant.EXCEPTION
import com.target.targetcasestudy.constants.TestConstant.ID
import com.target.targetcasestudy.constants.TestConstant.IMAGE_URL
import com.target.targetcasestudy.constants.TestConstant.REGULAR_PRICE
import com.target.targetcasestudy.constants.TestConstant.SALE_PRICE
import com.target.targetcasestudy.constants.TestConstant.STATUS
import com.target.targetcasestudy.constants.TestConstant.TEST_ERROR
import com.target.targetcasestudy.constants.TestConstant.TITLE
import com.target.targetcasestudy.domain.NetworkResult
import com.target.targetcasestudy.domain.entities.DealItem
import com.target.targetcasestudy.domain.repositoryinterfaces.DealsRepository
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.unmockkAll
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DealsListUseCaseTest {
    private lateinit var useCase: DealsListUseCase
    private lateinit var repository: DealsRepository
    private lateinit var dealsList: List<DealItem>

    @Before
    fun setUp() {
        repository = mockk()
        useCase = spyk(DealsListUseCase(repository))
        dealsList = listOf(
            DealItem(
                ID,
                SALE_PRICE,
                REGULAR_PRICE,
                STATUS,
                TITLE,
                DESCRIPTION,
                AISLE,
                IMAGE_URL
            ),
            DealItem(
                ID,
                SALE_PRICE,
                REGULAR_PRICE,
                STATUS,
                TITLE,
                DESCRIPTION,
                AISLE,
                IMAGE_URL
            ),
        )
    }

    @Test
    fun `test get deals success`() {
        runTest {

            coEvery { repository.getDeals() } returns NetworkResult.Success(dealsList)

            val result = useCase.getDeals() as NetworkResult.Success

            assertEquals(2, result.data.size)
            assertEquals(SALE_PRICE, result.data[0].salePrice)

        }
    }

    @Test
    fun `test get deals fails`() {
        runTest {

            coEvery { repository.getDeals() } returns NetworkResult.Error(TEST_ERROR)

            val result = useCase.getDeals() as NetworkResult.Error

            assertEquals(TEST_ERROR, result.message)

        }
    }

    @Test
    fun `test get deals exception`() {
        runTest {

            coEvery { repository.getDeals() } returns NetworkResult.Exception(Exception(EXCEPTION))

            val result = useCase.getDeals() as NetworkResult.Exception

            assertEquals(EXCEPTION, result.e.message)

        }
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}