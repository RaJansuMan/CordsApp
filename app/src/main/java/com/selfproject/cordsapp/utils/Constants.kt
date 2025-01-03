package com.selfproject.cordsapp.utils

import com.selfproject.cordsapp.domain.model.Layer

data class Constants(
    val defaultFolderId: Int = 0,
    val defaultLayer: Layer = Layer(
        layerId = "D",
        name = "Default",
        colourCode = 0xFFFFF
    )
)