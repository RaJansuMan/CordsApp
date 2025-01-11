package com.selfproject.cordsapp.presentation.navigation

sealed class Route(
    val route: String
) {
    data object LocateScreen : Route(route = "locate_screen")
    data object ArchiveScreen : Route(route = "archive_screen")
    data object HomeScreen : Route(route = "home_screen")
    data object AddPointScreen : Route(route = "add_point_screen")
    data object PointDetailsScreen : Route(route = "point_detail_screen/{param}") {
        fun createRoute(param: Int): String {
            return "point_detail_screen/$param"
        }
    }
}