package com.selfproject.cordsapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "folders")
data class FolderEntity(
    @PrimaryKey(autoGenerate = true) val folderId: Int = 0,
    val name: String,
    val description: String,
    val createdOn: Date
)