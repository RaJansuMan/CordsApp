package com.selfproject.cordsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.selfproject.cordsapp.data.local.dao.FolderDao
import com.selfproject.cordsapp.data.local.dao.PointDao
import com.selfproject.cordsapp.data.local.entity.FolderEntity
import com.selfproject.cordsapp.data.local.entity.PointEntity

@Database(
    entities = [PointEntity::class, FolderEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Convertors::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pointDao(): PointDao
    abstract fun folderDao(): FolderDao
}