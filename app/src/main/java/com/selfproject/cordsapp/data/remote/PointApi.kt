package com.selfproject.cordsapp.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface PointApi {
    @POST("get_point")
    suspend fun getRemotePoint(@Body pointData: PointQuery): Response<ResponseCoordinates>


    companion object {
        const val BASE_URL = "https://cordsappbackend.onrender.com"
    }
}