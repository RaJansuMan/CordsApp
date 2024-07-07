package com.selfproject.cordsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.selfproject.cordsapp.presentation.locate.LocateScreen
import androidx.navigation.compose.composable
import com.selfproject.cordsapp.presentation.ArchiveScreen

@Composable
fun TabNavGraph(navController: NavHostController) {

    NavHost(navController =navController , startDestination = Route.LocateScreen.route ){
        composable(route = Route.LocateScreen.route){
            LocateScreen()
        }
        composable(route = Route.ArchiveScreen.route){
            ArchiveScreen()
        }
    }
}