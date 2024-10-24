package com.selfproject.cordsapp.data.repositoy

import com.selfproject.cordsapp.data.remote.PointApi
import com.selfproject.cordsapp.domain.model.Point
import com.selfproject.cordsapp.domain.model.Result
import com.selfproject.cordsapp.domain.repository.PointRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PointRepositoryImpl @Inject constructor(
    private val api: PointApi
) : PointRepository {
    override suspend fun getPointDetailsRemote(point: Point, isSave: Boolean): Flow<Result<Point>> {
        return flow {

        }
    }
}