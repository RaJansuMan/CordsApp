package com.selfproject.cordsapp.presentation.pointDetails

import com.selfproject.cordsapp.domain.model.coordinateModel.Point

data class PointScreenState(
    val point: Point? = null,
    val toast: String? = null
)