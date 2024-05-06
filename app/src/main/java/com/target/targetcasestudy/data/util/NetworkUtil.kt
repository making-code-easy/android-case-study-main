package com.target.targetcasestudy.data.util

import com.squareup.moshi.Moshi
import com.target.targetcasestudy.data.response.ErrorResponse
import com.target.targetcasestudy.domain.NetworkResult
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class NetworkUtil @Inject constructor() {

    suspend fun <T : Any> handleApi(
        execute: suspend () -> Response<T>
    ): NetworkResult<T> {
        return try {
            val response = execute()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                NetworkResult.Success(body)
            } else {
                val moshiAdapter = Moshi.Builder().build().adapter(ErrorResponse::class.java)
                val r = response.errorBody()?.string()?.let { moshiAdapter.fromJson(it) }
                if (r != null) {
                    NetworkResult.Error(r.message)
                } else {
                    NetworkResult.Error("empty error")
                }

            }
        } catch (e: HttpException) {
            NetworkResult.Error(e.message())
        } catch (e: Throwable) {
            NetworkResult.Exception(e)
        }
    }
}

