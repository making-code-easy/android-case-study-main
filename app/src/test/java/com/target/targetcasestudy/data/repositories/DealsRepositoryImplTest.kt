package com.target.targetcasestudy.data.repositories

import com.target.targetcasestudy.constants.TestConstant
import com.target.targetcasestudy.constants.TestConstant.AISLE
import com.target.targetcasestudy.constants.TestConstant.DESCRIPTION
import com.target.targetcasestudy.constants.TestConstant.ID
import com.target.targetcasestudy.constants.TestConstant.IMAGE_URL
import com.target.targetcasestudy.constants.TestConstant.REGULAR_PRICE
import com.target.targetcasestudy.constants.TestConstant.SALE_PRICE
import com.target.targetcasestudy.constants.TestConstant.STATUS
import com.target.targetcasestudy.constants.TestConstant.TITLE
import com.target.targetcasestudy.data.datasource.remote.RemoteDataSourceImpl
import com.target.targetcasestudy.domain.NetworkResult
import com.target.targetcasestudy.domain.entities.DealItem
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class DealsRepositoryImplTest {

    private lateinit var repositoryImpl: DealsRepositoryImpl
    private lateinit var remoteDataSourceImpl: RemoteDataSourceImpl
    private lateinit var dealsList: List<DealItem>
    private lateinit var dealItem: DealItem

    @Before
    fun setUp() {
        remoteDataSourceImpl = mockk()
        repositoryImpl = spyk(DealsRepositoryImpl(remoteDataSourceImpl))
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

        dealItem = DealItem(
            ID,
            SALE_PRICE,
            REGULAR_PRICE,
            STATUS,
            TITLE,
            DESCRIPTION,
            AISLE,
            IMAGE_URL
        )
    }

    @Test
    fun `test get deals success`() {
        runTest {

            coEvery { remoteDataSourceImpl.getDeals() } returns NetworkResult.Success(dealsList)

            val result = (repositoryImpl.getDeals() as NetworkResult.Success).data

            Assert.assertEquals(2, result.size)
            Assert.assertEquals(SALE_PRICE, result[0].salePrice)

        }

    }

    @Test
    fun `test get deals fails`() {
        runTest {

            coEvery { remoteDataSourceImpl.getDeals() } returns NetworkResult.Error(TestConstant.TEST_ERROR)

            val result = repositoryImpl.getDeals() as NetworkResult.Error

            Assert.assertEquals(TestConstant.TEST_ERROR, result.message)

        }
    }

    @Test
    fun `test get deals exception`() {
        runTest {

            coEvery { remoteDataSourceImpl.getDeals() } returns NetworkResult.Exception(
                Exception(
                    TestConstant.EXCEPTION
                )
            )

            val result = repositoryImpl.getDeals() as NetworkResult.Exception

            Assert.assertEquals(TestConstant.EXCEPTION, result.e.message)

        }
    }

    //can be broken into multiples test, but due to time constraint adding in one test
    @Test
    fun `test get deals detail`() {
        runTest {

            coEvery { remoteDataSourceImpl.getDealsDetail(ID) } returns NetworkResult.Success(
                dealItem
            )

            val result = (repositoryImpl.getDealsDetails(ID) as NetworkResult.Success).data

            Assert.assertEquals(ID, result.id)
            Assert.assertEquals(SALE_PRICE, result.salePrice)
            Assert.assertEquals(REGULAR_PRICE, result.regularPrice)
            Assert.assertEquals(DESCRIPTION, result.description)
            Assert.assertEquals(TITLE, result.title)
            Assert.assertEquals(STATUS, result.status)
            Assert.assertEquals(AISLE, result.aisle)
            Assert.assertEquals(IMAGE_URL, result.imageUrl)

        }
    }

    @Test
    fun `test get deals detail fails`() {
        runTest {

            coEvery { remoteDataSourceImpl.getDealsDetail(ID) } returns NetworkResult.Error(
                TestConstant.TEST_ERROR
            )

            val result = repositoryImpl.getDealsDetails(ID) as NetworkResult.Error

            Assert.assertEquals(TestConstant.TEST_ERROR, result.message)

        }
    }

    @Test
    fun `test get deals detail exception`() {
        runTest {

            coEvery { remoteDataSourceImpl.getDealsDetail(ID) } returns NetworkResult.Exception(
                Exception(
                    TestConstant.EXCEPTION
                )
            )

            val result = repositoryImpl.getDealsDetails(ID) as NetworkResult.Exception

            Assert.assertEquals(TestConstant.EXCEPTION, result.e.message)

        }
    }
}