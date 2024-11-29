package com.selfproject.cordsapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.selfproject.cordsapp.data.local.entity.FolderEntity
import com.selfproject.cordsapp.data.local.entity.FolderWithPoints
import com.selfproject.cordsapp.data.local.entity.PointEntity

@Dao
interface FolderPointDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFolder(folder: FolderEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPoint(point: PointEntity)

    @Transaction
    @Query("SELECT * FROM folders WHERE folderId = :folderId")
    suspend fun getFolderWithPoints(folderId: Int): FolderWithPoints

    @Transaction
    @Query("SELECT * FROM folders")
    suspend fun getAllFoldersWithPoints(): List<FolderWithPoints>
}