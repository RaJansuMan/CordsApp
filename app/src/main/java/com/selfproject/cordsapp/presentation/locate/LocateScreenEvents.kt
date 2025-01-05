package com.selfproject.cordsapp.presentation.locate

sealed class LocateScreenEvents {
    data class OnPointClick(val pointId: Int? = null, val layerId: String? = null) :
        LocateScreenEvents()

    data object OnLeftClick : LocateScreenEvents()
    data object OnRightClick : LocateScreenEvents()
    data object OnUpClick : LocateScreenEvents()
    data object OnDeletePoint : LocateScreenEvents()
    data object OnPointDetails : LocateScreenEvents()
}