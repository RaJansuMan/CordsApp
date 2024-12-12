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
import com.selfproject.cordsapp.domain.model.Layer
import com.selfproject.cordsapp.domain.model.Result
import com.selfproject.cordsapp.domain.model.coordinateModel.Point
import com.selfproject.cordsapp.domain.repository.FileRepository
import com.selfproject.cordsapp.domain.repository.PointRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FilePointRepositoryImpl @Inject constructor(
    private val api: PointApi,
    db: AppDatabase
) : PointRepository, FileRepository {

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

    override suspend fun deletePoint(point: Point): Flow<Result<Void>> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllPoint(folderId: Int): Flow<Result<FolderWithPoint>> {
        TODO("Not yet implemented")
    }

    override suspend fun getPointDetailFromDatabase(pointId: Int): Flow<Result<Point>> {
        TODO("Not yet implemented")
    }

    override fun getPointDetail(pointId: Int): Point {
        TODO("Not yet implemented")
    }

    override suspend fun getLayerList(folderId: Int): Flow<Result<List<Layer>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getFolders(): Flow<Result<List<Folder>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getFolderDetail(folderId: Int): Flow<Result<Folder>> {
        TODO("Not yet implemented")
    }

    override suspend fun addFolder(folder: Folder): Flow<Result<Folder>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFolder(folderId: Int): Flow<Result<Void>> {
        TODO("Not yet implemented")
    }

    override suspend fun addLayer(folderId: Int, layer: Layer): Flow<Result<Layer>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteLayer(folderId: Int, layer: Layer): Flow<Result<Void>> {
        TODO("Not yet implemented")
    }
}