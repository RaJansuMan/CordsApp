package com.selfproject.cordsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.selfproject.cordsapp.presentation.addPoint.AddPointViewModel
import com.selfproject.cordsapp.presentation.addPoint.views.AddPointScreen
import com.selfproject.cordsapp.presentation.home.HomeScreen
import com.selfproject.cordsapp.presentation.pointDetails.PointDetailViewModel
import com.selfproject.cordsapp.presentation.pointDetails.views.PointDetails

@Composable
fun HomeNavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Route.HomeScreen.route) {
        composable(route = Route.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Route.AddPointScreen.route) {
            val viewModel: AddPointViewModel = hiltViewModel()
            AddPointScreen(navController = navController, viewModel = viewModel)
        }

        composable(
            route = Route.PointDetailsScreen.route,
            arguments = listOf(navArgument("param") { type = NavType.IntType })
        ) {
            val viewModel: PointDetailViewModel = hiltViewModel()
            PointDetails(navController = navController, viewModel = viewModel)
        }
    }
}