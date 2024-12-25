package com.selfproject.cordsapp.presentation.locate

import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager
import com.selfproject.cordsapp.domain.model.Layer
import com.selfproject.cordsapp.domain.model.coordinateModel.Point

data class LayerData(
    val layer: Layer,
    val points: MutableList<Point>,
    var annotationManager: PointAnnotationManager? = null,
)