package com.selfproject.cordsapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.selfproject.cordsapp.data.local.entity.FolderWithPointsEntity
import com.selfproject.cordsapp.data.local.entity.PointEntity
import com.selfproject.cordsapp.domain.model.Layer

@Dao
interface PointDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPoint(point: PointEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPoints(points: List<PointEntity>): List<Long>

    @Update
    suspend fun updatePoint(point: PointEntity)

    @Delete
    suspend fun deletePoint(point: PointEntity)

    @Query("SELECT * FROM points WHERE pointId = :pointId")
    suspend fun getPointById(pointId: Int): PointEntity?


    @Query("SELECT * FROM points WHERE folderId = :folderId")
    suspend fun getPointsByFolderId(folderId: Int): List<PointEntity>

    @Transaction
    @Query("SELECT * FROM folders WHERE folderId = :folderId")
    suspend fun getFolderWithPoints(folderId: Int): FolderWithPointsEntity

    @Query("DELETE FROM points WHERE folderId = :folderId AND layer = :layer")
    suspend fun deletePointsByFolderAndLayer(folderId: Int, layer: Layer)

    @Query("SELECT * FROM points WHERE folderId = :folderId AND layer = :layer")
    suspend fun getPointsByFolderAndLayer(folderId: Int, layer: Layer): List<PointEntity>
}