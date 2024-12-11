package com.selfproject.cordsapp.domain.model

import com.selfproject.cordsapp.domain.model.coordinateModel.Point

data class FolderWithPoint(val folder: Folder, val points: List<Point>)