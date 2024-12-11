package com.selfproject.cordsapp.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class FolderWithPointsEntity(
    @Embedded val folder: FolderEntity,
    @Relation(
        parentColumn = "folderId",
        entityColumn = "folderId"
    )
    val points: List<PointEntity>
)