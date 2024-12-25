package com.selfproject.cordsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.selfproject.cordsapp.presentation.ArchiveScreen
import com.selfproject.cordsapp.presentation.locate.LocateViewModel
import com.selfproject.cordsapp.presentation.locate.views.LocateScreen

@Composable
fun TabNavGraph(tabNavController: NavHostController, homeNavController: NavController) {

    NavHost(navController = tabNavController, startDestination = Route.LocateScreen.route) {
        composable(route = Route.LocateScreen.route) {
            val viewModel: LocateViewModel = hiltViewModel()
            LocateScreen(homeNavController, viewModel)
        }
        composable(route = Route.ArchiveScreen.route) {
            ArchiveScreen()
        }

    }
}