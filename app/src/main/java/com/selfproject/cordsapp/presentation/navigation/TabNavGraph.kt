package com.selfproject.cordsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.selfproject.cordsapp.presentation.ArchiveScreen
import com.selfproject.cordsapp.presentation.locate.LocateScreen

@Composable
fun TabNavGraph(tabNavController: NavHostController,homeNavController:NavController) {

    NavHost(navController = tabNavController, startDestination = Route.LocateScreen.route) {
        composable(route = Route.LocateScreen.route) {
            LocateScreen(homeNavController)
        }
        composable(route = Route.ArchiveScreen.route) {
            ArchiveScreen()
        }

    }
}