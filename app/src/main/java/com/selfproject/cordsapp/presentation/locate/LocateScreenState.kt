package com.selfproject.cordsapp.presentation.locate

import com.selfproject.cordsapp.domain.model.FolderWithPoint

data class LocateScreenState(
    val clickedPoint: Int? = null,
    val folderWithPoint: FolderWithPoint
)