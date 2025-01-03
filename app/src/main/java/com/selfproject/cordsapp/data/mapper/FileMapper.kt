package com.selfproject.cordsapp.data.mapper

import com.selfproject.cordsapp.data.local.entity.FolderEntity
import com.selfproject.cordsapp.data.local.entity.FolderWithPointsEntity
import com.selfproject.cordsapp.domain.model.Folder
import com.selfproject.cordsapp.domain.model.FolderWithPoint

fun Folder.toFolderEntity(): FolderEntity {
    return FolderEntity(
        folderId = folderId,
        name = name,
        description = description,
        createdOn = createdOn,
        layers = layers.toMutableList()
    )
}

fun FolderEntity.toFolder(): Folder {
    return Folder(
        folderId = folderId,
        name = name,
        description = description,
        createdOn = createdOn,
        layers = layers.toList()
    )
}

fun FolderWithPointsEntity.toFolderWithPoint(): FolderWithPoint {
    val points = points
        .filter { it.pointId != null }
        .groupBy { it.layer.layerId }
        .mapValues { entry ->
            entry.value.associate {
                it.pointId!! to it.toPoint()
            }
        }
    return FolderWithPoint(
        folder = folder.toFolder(),
        pointsWithLayer = points.mapValues { it.value.toMutableMap() }.toMutableMap()
    )
}