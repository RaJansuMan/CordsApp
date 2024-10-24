package com.selfproject.cordsapp.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface PointApi {
    @POST("GetPoint")
    suspend fun sendStockData(@Body stockData: PointQuery): Response<Unit>

    companion object {
        const val API_KEY = "JI7KYLN2NQIKY7KG"
        const val BASE_URL = "https://alphavantage.co"
    }
}