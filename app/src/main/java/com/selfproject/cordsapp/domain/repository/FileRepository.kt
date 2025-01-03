package com.selfproject.cordsapp.domain.repository

import com.selfproject.cordsapp.domain.model.Folder
import com.selfproject.cordsapp.domain.model.Layer
import com.selfproject.cordsapp.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface FileRepository {
    suspend fun getLayerList(folderId: Int): Flow<Result<List<Layer>>>
    suspend fun getFolders(): Flow<Result<List<Folder>>>
    suspend fun getFolderDetail(folderId: Int?): Flow<Result<Folder>>
    suspend fun addFolder(folder: Folder): Flow<Result<Folder>>
    suspend fun deleteFolder(folderId: Int): Flow<Result<Void>>
    suspend fun addLayer(folderId: Int, layer: Layer): Flow<Result<Layer>>
    suspend fun deleteLayer(folderId: Int, layer: Layer): Flow<Result<Void>>
}