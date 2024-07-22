package com.selfproject.cordsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.selfproject.cordsapp.presentation.addPoint.AddPointScreen
import com.selfproject.cordsapp.presentation.home.HomeScreen
import com.selfproject.cordsapp.presentation.locate.LocateScreen

@Composable
fun HomeNavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Route.HomeScreen.route) {
        composable(route = Route.HomeScreen.route) {
            HomeScreen(navController=navController)
        }
        composable(route = Route.InputScreen.route) {
            AddPointScreen(navController)
        }
        composable(route = Route.LocateScreen.route) {
            LocateScreen(navController)
        }
    }
}