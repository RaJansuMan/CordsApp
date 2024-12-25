package com.selfproject.cordsapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.selfproject.cordsapp.data.local.entity.FolderEntity
import com.selfproject.cordsapp.data.local.entity.FolderWithPointsEntity

@Dao
interface FolderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFolder(folder: FolderEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFolders(folders: List<FolderEntity>): List<Long>

    @Query("SELECT * FROM folders WHERE folderId = :folderID")
    suspend fun getFolderById(folderID: Int): FolderEntity?

    @Update
    suspend fun updateFolder(folder: FolderEntity)

    @Delete
    suspend fun deleteFolder(folder: FolderEntity)

    @Query("SELECT * FROM folders")
    suspend fun getAllFolders(): List<FolderEntity>

    @Transaction
    @Query("SELECT * FROM folders WHERE folderId = :folderId")
    suspend fun getFolderWithPoints(folderId: Int): FolderWithPointsEntity?
}