package com.selfproject.cordsapp.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(
    tableName = "points",
    foreignKeys = [
        ForeignKey(
            entity = FolderEntity::class,
            parentColumns = ["folderId"],
            childColumns = ["folderId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["folderId"])]
)
data class PointEntity(
    @PrimaryKey(autoGenerate = true) val pointId: Int = 0,
    val folderId: Int,
    val lat: Double,
    val lon: Double,
    val createdOn: Date,
    val east: Double,
    val north: Double,
    val zoneLetter: Char,
    val zoneNumber: Int,
    val eleEllip: Double,
    val eleEgm08: Double,
    val eleEgm96: Double,
    val description: String,
    val pointLabel: Char,
//    val pontNo : Int
)