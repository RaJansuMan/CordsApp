package com.selfproject.cordsapp.data.mapper

import com.google.gson.Gson
import com.selfproject.cordsapp.data.local.entity.PointEntity
import com.selfproject.cordsapp.data.remote.ApiResponse
import com.selfproject.cordsapp.data.remote.ErrorResponse
import com.selfproject.cordsapp.data.remote.ResponseCoordinates
import com.selfproject.cordsapp.domain.model.coordinateModel.Point
import retrofit2.Response

fun <T> Response<T>.toApiResponse(): ApiResponse<T> {
    return if (isSuccessful) {
        val body = body()
        if (body != null) {
            ApiResponse.Success(body)
        } else {
            ApiResponse.Error(code(), "Empty body")
        }
    } else {
        val errorBody = errorBody()?.string()
        val errorResponse = try {
            Gson().fromJson(errorBody, ErrorResponse::class.java)
        } catch (e: Exception) {
            null
        }
        if (errorResponse != null) {
            ApiResponse.Error(errorResponse.errorCode, errorResponse.errorMessage)
        } else {
            ApiResponse.Error(code(), message())
        }
    }
}

fun ResponseCoordinates.toPointEntity(point: Point): PointEntity {
    return PointEntity(
        pointId = point.pointId,
        folderId = point.folderId,
        lat = lat,
        lon = lon,
        createdOn = point.createdOn,
        east = east,
        north = north,
        zoneLetter = zoneLetter,
        zoneNumber = zoneNumber,
        eleEllip = ellipsoidal,
        eleEgm08 = egm08,
        eleEgm96 = egm96,
        description = point.description,
        layer = point.layer,
        pointNo = point.pointNumber
    )
}