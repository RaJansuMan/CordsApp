package com.selfproject.cordsapp.data.repositoy

import android.util.Log
import com.selfproject.cordsapp.data.local.AppDatabase
import com.selfproject.cordsapp.data.mapper.pointToPointQuery
import com.selfproject.cordsapp.data.mapper.toApiResponse
import com.selfproject.cordsapp.data.mapper.toFolder
import com.selfproject.cordsapp.data.mapper.toFolderEntity
import com.selfproject.cordsapp.data.mapper.toFolderWithPoint
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
    private var pointCache: MutableMap<String, MutableMap<Int, Point>> = mutableMapOf()
    private var currentFolderCache: Folder? = null

    override suspend fun addPoint(point: Point): Flow<Result<Point>> {
        return flow {
            emit(Result.Loading())
            val response = try {
//                val moshi = Moshi.Builder().build()
//                val type = Types.newParameterizedType(
//                    Response::class.java,
//                    ResponseCoordinates::class.java
//                )
//                val adapter = moshi.adapter<Response<ResponseCoordinates>>(type)

                val query = point.toPointQuery()
                Log.d("testtry", "here1")
                val result = api.getRemotePoint(query)
                Log.d("testtry", "here2")
                val response = result.toApiResponse()
                Log.d("testtry", "here3")
                when (response) {
                    is ApiResponse.Error -> {
                        emit(Result.Error(response.errorMessage))
                        null
                    }

                    is ApiResponse.Success -> {
                        response.data.toPointEntity(point)
                    }
                }
            } catch (e: Exception) {
                Log.d("testtry", e.toString())
                emit(Result.Error("Unable to connect to server"))
                null
            }
            response?.let { pointEntity ->
                val id = pointDao.insertPoint(pointEntity)
                val newPoint = pointDao.getPointById(id.toInt())?.toPoint()
                if (newPoint?.pointId == null) {
                    emit(Result.Error("Unable to save to repository"))
                } else {
                    if (!pointCache.containsKey(newPoint.layer.layerId)) {
                        pointCache[newPoint.layer.layerId] = mutableMapOf()
                    }
                    pointCache[newPoint.layer.layerId]!![newPoint.pointId] = newPoint
                    emit(Result.Success(data = newPoint))
                }
            }
            emit(Result.Loading(false))
        }
    }

    override suspend fun deletePoint(point: Point): Flow<Result<Void>> {
        return flow {
            emit(Result.Loading())
            pointDao.deletePoint(point.pointToPointQuery())
            emit(Result.Success(data = null))
            point.pointId?.let { pointCache[point.layer.layerId]?.remove(it) }
            emit(Result.Loading(false))
        }
    }

    override suspend fun getAllPoint(folderId: Int): Flow<Result<FolderWithPoint>> {
        return flow {
            emit(Result.Loading())
            val folderWithPoint = folderDao.getFolderWithPoints(folderId)
            if (folderWithPoint == null) {
                emit(Result.Error("No such folder found"))
            } else {
                pointCache = mutableMapOf()
                val result = folderWithPoint.toFolderWithPoint()
                pointCache = result.pointsWithLayer
                emit(Result.Success(result))
            }
            emit(Result.Loading(false))
        }
    }

    override suspend fun getPointDetailFromDatabase(pointId: Int): Flow<Result<Point>> {
        return flow {
            emit(Result.Loading())
            val newPoint = pointDao.getPointById(pointId)?.toPoint()
            if (newPoint == null) {
                emit(Result.Error("Unable to save to repository"))
            } else {
                emit(Result.Success(data = newPoint))
            }
            emit(Result.Loading(false))
        }
    }

    override fun getPointDetail(pointId: Int, layerId: String?): Result<Point> {
        if (layerId != null) {
            val layPoint = pointCache[layerId]
            if (layPoint == null) {
                return Result.Error("Layer Id not found")
            } else {
                val point = layPoint[pointId]
                return if (point == null) {
                    Result.Error("Point Id not found")
                } else {
                    Result.Success(point)
                }
            }
        }
        for (layer in pointCache.values) {
            if (layer.containsKey(pointId)) {
                return Result.Success(layer[pointId])
            }
        }
        return Result.Error("Point Id not found")
    }

    override fun getPointLastId(layerId: String): Result<Int> {
        return if (pointCache.containsKey(layerId)) {
            Result.Success(
                pointCache[layerId]?.values?.lastOrNull()?.pointNumber?.let { it + 1 } ?: 0
            )
        } else
            Result.Error("layer not found")
    }

    override suspend fun getLayerList(folderId: Int): Flow<Result<List<Layer>>> {
        return flow {
            emit(Result.Loading())
            if (folderId == currentFolderCache?.folderId) {
                emit(Result.Success(currentFolderCache!!.layers))
            } else {
                val folder = folderDao.getFolderById(folderId)
                if (folder == null) {
                    emit(Result.Error("No folder found with such id"))
                } else {
                    emit(Result.Success(folderDao.getFolderById(folderId)!!.layers))
                }
            }
            emit(Result.Loading(false))
        }
    }

    override suspend fun getFolders(): Flow<Result<List<Folder>>> {
        return flow {
            emit(Result.Loading())
            emit(Result.Success(folderDao.getAllFolders().map { it.toFolder() }))
            emit(Result.Loading(false))

        }
    }

    override suspend fun getFolderDetail(folderId: Int?): Flow<Result<Folder>> {
        return flow {
            emit(Result.Loading())
            if (folderId == null) {
                if (currentFolderCache != null) {
                    emit(Result.Success(currentFolderCache))
                } else {
                    emit(Result.Error("No Folder open"))
                }
            } else {
                val newFolder = folderDao.getFolderById(folderId)?.toFolder()
                if (newFolder == null) {
                    emit(Result.Success(null))
                } else {
                    emit(Result.Success(newFolder))
                    currentFolderCache = newFolder
                }
            }
            emit(Result.Loading(false))
        }
    }

    override suspend fun addFolder(folder: Folder): Flow<Result<Folder>> {
        return flow {
            emit(Result.Loading())
            val id = folderDao.insertFolder(folder.toFolderEntity())
            val newFolder = folderDao.getFolderById(id.toInt())?.toFolder()
            if (newFolder == null) {
                emit(Result.Error("Unable to add the folder"))
            } else {
                emit(Result.Success(newFolder))
            }
            emit(Result.Loading(false))
        }
    }

    override suspend fun deleteFolder(folderId: Int): Flow<Result<Void>> {
        TODO("Not yet implemented")
    }

    override suspend fun addLayer(folderId: Int, layer: Layer): Flow<Result<Layer>> {
        return flow {
            emit(Result.Loading())
            val folder = folderDao.getFolderById(folderId)
            if (folder == null) {
                emit(Result.Error("No folder found"))
            } else {
                folder.layers.add(layer)
                folderDao.insertFolder(folder)
                emit(Result.Success(layer))
            }
            emit(Result.Loading(false))
        }
    }

    override suspend fun deleteLayer(folderId: Int, layer: Layer): Flow<Result<Void>> {
        TODO("Not yet implemented")
    }

}