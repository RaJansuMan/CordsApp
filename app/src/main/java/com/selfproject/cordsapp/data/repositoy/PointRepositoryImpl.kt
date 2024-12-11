package com.selfproject.cordsapp.data.repositoy

import com.selfproject.cordsapp.data.local.AppDatabase
import com.selfproject.cordsapp.data.mapper.toApiResponse
import com.selfproject.cordsapp.data.mapper.toPoint
import com.selfproject.cordsapp.data.mapper.toPointEntity
import com.selfproject.cordsapp.data.mapper.toPointQuery
import com.selfproject.cordsapp.data.remote.ApiResponse
import com.selfproject.cordsapp.data.remote.PointApi
import com.selfproject.cordsapp.domain.model.Folder
import com.selfproject.cordsapp.domain.model.FolderWithPoint
import com.selfproject.cordsapp.domain.model.Result
import com.selfproject.cordsapp.domain.model.coordinateModel.Point
import com.selfproject.cordsapp.domain.repository.PointRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PointRepositoryImpl @Inject constructor(
    private val api: PointApi,
    db: AppDatabase
) : PointRepository {

    private val folderDao = db.folderDao()
    private val pointDao = db.pointDao()
    private val pointCache: MutableMap<Int, Point> = mutableMapOf()
    private val currentFolderCache: Folder? = null
    override suspend fun addPoint(point: Point): Flow<Result<Point>> {
        return flow {
            emit(Result.Loading())
            val response = try {
                when (val result = api.getRemotePoint(point.toPointQuery()).toApiResponse()) {
                    is ApiResponse.Error -> {
                        emit(Result.Error(result.errorMessage))
                        null
                    }

                    is ApiResponse.Success -> {
                        result.data.toPointEntity(point)
                    }
                }
            } catch (e: Exception) {
                emit(Result.Error(e.message ?: "Unknown error"))
                null
            }
            response?.let { pointEntity ->
                pointDao.insertPoint(pointEntity)
                emit(Result.Success(data = pointEntity.toPoint()))
            }
            emit(Result.Loading(false))
        }
    }

    override suspend fun deletePoint(point: Point): Flow<Result<Point>> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllPoint(point: Point): Flow<Result<FolderWithPoint>> {
        TODO("Not yet implemented")
    }

    override suspend fun getPointDetail(pointId: Int): Flow<Result<Point>> {
        TODO("Not yet implemented")
    }

}