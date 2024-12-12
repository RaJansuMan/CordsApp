package com.selfproject.cordsapp.data.mapper

import com.selfproject.cordsapp.data.local.entity.FolderEntity
import com.selfproject.cordsapp.data.local.entity.FolderWithPointsEntity
import com.selfproject.cordsapp.domain.model.Folder
import com.selfproject.cordsapp.domain.model.FolderWithPoint

fun Folder.toFolderEntity(): FolderEntity {
    return FolderEntity(
        name = name,
        description = description,
        createdOn = createdOn,
        layers = layers
    )
}

fun FolderEntity.toFolder(): Folder {
    return Folder(
        folderId = folderId,
        name = name,
        description = description,
        createdOn = createdOn,
        layers = layers
    )
}

fun FolderWithPointsEntity.toFolderWithPoint(): FolderWithPoint {
    return FolderWithPoint(
        folder = folder.toFolder(),
        points = points.forEach()
    )
}