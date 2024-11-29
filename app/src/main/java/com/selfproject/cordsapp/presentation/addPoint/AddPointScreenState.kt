package com.selfproject.cordsapp.presentation.addPoint

import com.selfproject.cordsapp.domain.coordinateModel.CoordinateSystemType
import com.selfproject.cordsapp.domain.coordinateModel.ElevationType
import com.selfproject.cordsapp.domain.model.InputForm

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
    var isProgress: Boolean = false
)