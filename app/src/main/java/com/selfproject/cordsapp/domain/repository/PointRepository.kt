package com.selfproject.cordsapp.domain.repository

import com.selfproject.cordsapp.domain.coordinateModel.Point
import com.selfproject.cordsapp.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface PointRepository {
    suspend fun getPointDetailsRemote(point: Point, isSave: Boolean): Flow<Result<Point>>
}