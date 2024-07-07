package com.selfproject.cordsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.selfproject.cordsapp.presentation.home.HomeScreen
import com.selfproject.cordsapp.presentation.locate.LocateScreen

@Composable
fun HomeNavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Route.HomeScreen.route){
       composable(route = Route.HomeScreen.route){
           HomeScreen()
       }
        composable(route = Route.InputScreen.route){

        }
        composable(route = Route.LocateScreen.route){
            LocateScreen()
        }
    }
}