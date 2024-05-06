package com.target.targetcasestudy

import com.target.targetcasestudy.data.repositories.DealsRepositoryImplTest
import com.target.targetcasestudy.domain.usecases.DealsListUseCaseTest
import com.target.targetcasestudy.domain.usecases.DetailPageUseCaseTest
import com.target.targetcasestudy.presentation.dealsdetail.viewmodels.DetailPageViewModelTest
import com.target.targetcasestudy.presentation.dealslist.viewmodels.DealsListViewModelTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.junit.runners.Suite

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(Suite::class)
@Suite.SuiteClasses(
    DealsRepositoryImplTest::class,
    DealsListUseCaseTest::class,
    DetailPageUseCaseTest::class,
    DetailPageViewModelTest::class,
    DealsListViewModelTest::class
)
class DealsTestSuit {
}