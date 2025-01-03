package com.selfproject.cordsapp.presentation.addPoint

import com.selfproject.cordsapp.domain.model.Folder
import com.selfproject.cordsapp.domain.model.InputForm
import com.selfproject.cordsapp.domain.model.coordinateModel.CoordinateSystemType
import com.selfproject.cordsapp.domain.model.coordinateModel.ElevationType

data class AddPointScreenState(
    var coordinateSystemType: CoordinateSystemType = CoordinateSystemType.WGS84,
    var elevationType: ElevationType = ElevationType.ELLIPSOIDAL,
    var latitude: InputForm = InputForm(),
    var longitude: InputForm = InputForm(),
    var elevation: InputForm = InputForm(),
    var zoneLetter: InputForm = InputForm(),
    var zoneNumber: InputForm = InputForm(),
    var easting: InputForm = InputForm(),
    var northing: InputForm = InputForm(),
    var isProgress: Boolean = false,
    var pointNo: InputForm = InputForm(),
    var description: InputForm = InputForm(),
    var layerList: List<String> = emptyList(),
    var selectedLayer: String = "",
    val folder: Folder? = null,
    val folderFetchProgress: Boolean = true
)