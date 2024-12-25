package com.selfproject.cordsapp.presentation.locate

sealed class LocateScreenEvents {
    data class OnPointClick(val pointId: Int) : LocateScreenEvents()
}