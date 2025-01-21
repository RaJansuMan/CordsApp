package com.selfproject.cordsapp.data.remote

import androidx.annotation.Keep

@Keep
data class ResponseCoordinates(
    val lat: Double,
    val lon: Double,
    val east: Double,
    val north: Double,
    val ellipsoidal: Double,
    val egm08: Double,
    val egm96: Double,
    val zoneLetter: Char,
    val zoneNumber: Int

)

@Keep
data class ErrorResponse(
    val errorCode: Int,
    val errorMessage: String
)