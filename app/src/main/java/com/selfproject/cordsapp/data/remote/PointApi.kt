package com.selfproject.cordsapp.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET

interface PointApi {
    @GET("GetPoint")
    suspend fun getRemotePoint(@Body pointData: PointQuery): Response<ResponseCoordinates>


    companion object {
        const val API_KEY = "JI7KYLN2NQIKY7KG"
        const val BASE_URL = "https://alphavantage.co"
    }
}