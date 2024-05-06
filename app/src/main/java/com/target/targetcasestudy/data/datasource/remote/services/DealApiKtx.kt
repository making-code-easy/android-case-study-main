package com.target.targetcasestudy.data.datasource.remote.services

import com.target.targetcasestudy.data.response.DealsListResponse
import com.target.targetcasestudy.data.response.DetailPageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

const val BASE_URL = "https://api.target.com/mobile_case_study_deals/v1/"

//can be created single service for whole project, but this time limited to this service only
interface DealApiKtx {

    @GET("deals")
    suspend fun retrieveDeals(): Response<DealsListResponse>

    @GET("deals/{dealId}")
    suspend fun retrieveDeal(@Path("dealId") dealId: String): Response<DetailPageResponse>
}
