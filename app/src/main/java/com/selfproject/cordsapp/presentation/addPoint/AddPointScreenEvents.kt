package com.selfproject.cordsapp.presentation.addPoint

sealed class AddPointScreenEvents {
    data object AddPoint : AddPointScreenEvents()
    data object ToastShowed : AddPointScreenEvents()
    data object OnBackClicked : AddPointScreenEvents()
}