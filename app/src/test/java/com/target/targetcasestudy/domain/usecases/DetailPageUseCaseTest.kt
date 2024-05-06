package com.target.targetcasestudy.domain.usecases

import com.target.targetcasestudy.constants.TestConstant
import com.target.targetcasestudy.constants.TestConstant.AISLE
import com.target.targetcasestudy.constants.TestConstant.DESCRIPTION
import com.target.targetcasestudy.constants.TestConstant.ID
import com.target.targetcasestudy.constants.TestConstant.IMAGE_URL
import com.target.targetcasestudy.constants.TestConstant.REGULAR_PRICE
import com.target.targetcasestudy.constants.TestConstant.SALE_PRICE
import com.target.targetcasestudy.constants.TestConstant.STATUS
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


class DetailPageUseCaseTest {
    private lateinit var useCase: DetailPageUseCase
    private lateinit var repository: DealsRepository
    private lateinit var dealItem: DealItem

    @Before
    fun setUp() {
        repository = mockk()
        useCase = spyk(DetailPageUseCase(repository))

        dealItem =
            DealItem(ID, SALE_PRICE, REGULAR_PRICE, STATUS, TITLE, DESCRIPTION, AISLE, IMAGE_URL)

    }


    //can be broken into multiples test, but due to time constraint adding in one test
    @Test
    fun `test get deals detail`() {
        runTest {

            coEvery { repository.getDealsDetails(ID) } returns NetworkResult.Success(dealItem)

            val result = (useCase.getDealDetails(ID) as NetworkResult.Success).data

            assertEquals(ID, result.id)
            assertEquals(SALE_PRICE, result.salePrice)
            assertEquals(REGULAR_PRICE, result.regularPrice)
            assertEquals(DESCRIPTION, result.description)
            assertEquals(TITLE, result.title)
            assertEquals(STATUS, result.status)
            assertEquals(AISLE, result.aisle)
            assertEquals(IMAGE_URL, result.imageUrl)

        }

    }

    @Test
    fun `test get deals fails`() {
        runTest {

            coEvery { repository.getDealsDetails(ID) } returns NetworkResult.Error(TestConstant.TEST_ERROR)

            val result = useCase.getDealDetails(ID) as NetworkResult.Error

            assertEquals(TestConstant.TEST_ERROR, result.message)

        }
    }

    @Test
    fun `test get deals exception`() {
        runTest {

            coEvery { repository.getDealsDetails(ID) } returns NetworkResult.Exception(
                Exception(
                    TestConstant.EXCEPTION
                )
            )

            val result = useCase.getDealDetails(ID) as NetworkResult.Exception

            assertEquals(TestConstant.EXCEPTION, result.e.message)

        }
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}