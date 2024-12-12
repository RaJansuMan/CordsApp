package com.selfproject.cordsapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.selfproject.cordsapp.domain.model.Layer
import java.sql.Date

@Entity(tableName = "folders")
data class FolderEntity(
    @PrimaryKey(autoGenerate = true) val folderId: Int = 0,
    val name: String,
    val description: String,
    val createdOn: Date,
    val layers: List<Layer>
)