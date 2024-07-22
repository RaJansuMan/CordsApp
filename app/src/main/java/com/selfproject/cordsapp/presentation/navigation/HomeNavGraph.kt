package com.selfproject.cordsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.selfproject.cordsapp.presentation.addPoint.AddPointScreen
import com.selfproject.cordsapp.presentation.addPoint.AddPointViewModel
import com.selfproject.cordsapp.presentation.home.HomeScreen

@Composable
fun HomeNavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Route.HomeScreen.route) {
        composable(route = Route.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Route.AddPointScreen.route) {
            val viewModel: AddPointViewModel = hiltViewModel()
            AddPointScreen(navController = navController,viewModel = viewModel)
        }
    }
}