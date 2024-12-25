package com.selfproject.cordsapp.domain.model

import com.selfproject.cordsapp.domain.model.coordinateModel.Point

data class FolderWithPoint(
    val folder: Folder,
    val pointsWithLayer: MutableMap<String, MutableMap<Int, Point>>
)