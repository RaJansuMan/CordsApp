package com.selfproject.cordsapp.presentation.locate

import com.selfproject.cordsapp.domain.model.FolderWithPoint
import com.selfproject.cordsapp.domain.model.coordinateModel.Point

data class LocateScreenState(
    val clickedPoint: Point? = null,
    val folderWithPoint: FolderWithPoint? = null,
    val isProgress: Boolean = false,
    val toastMessage: String? = null,
    val dynamicLayers: MutableMap<String, LayerData> = mutableMapOf()
)