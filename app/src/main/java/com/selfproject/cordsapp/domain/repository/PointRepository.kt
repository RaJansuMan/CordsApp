package com.selfproject.cordsapp.domain.repository

import com.selfproject.cordsapp.domain.model.FolderWithPoint
import com.selfproject.cordsapp.domain.model.Result
import com.selfproject.cordsapp.domain.model.coordinateModel.Point
import kotlinx.coroutines.flow.Flow

interface PointRepository {
    suspend fun addPoint(point: Point): Flow<Result<Point>>
    suspend fun deletePoint(point: Point): Flow<Result<Point>>
    suspend fun getAllPoint(point: Point): Flow<Result<FolderWithPoint>>
    suspend fun getPointDetail(pointId: Int): Flow<Result<Point>>
}