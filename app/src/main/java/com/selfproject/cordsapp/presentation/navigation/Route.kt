package com.selfproject.cordsapp.presentation.navigation

sealed class Route(
    val route:String
) {
    data object LocateScreen : Route (route = "locate_screen")
    data object ArchiveScreen :Route(route = "archive_screen")
    data object HomeScreen :Route(route = "home_screen")
    data object InputScreen : Route(route = "input_screen")
}