package com.selfproject.cordsapp.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET

interface PointApi {
    @GET("get_point")
    suspend fun getRemotePoint(@Body pointData: PointQuery): Response<ResponseCoordinates>


    companion object {
        //        const val API_KEY = "JI7KYLN2NQIKY7KG"
        const val BASE_URL = "http://192.168.1.9:5000"
    }
}