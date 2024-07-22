package com.selfproject.cordsapp.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.selfproject.cordsapp.presentation.navigation.Route
import com.selfproject.cordsapp.presentation.navigation.TabNavGraph
import com.selfproject.cordsapp.ui.theme.lightBlack
import com.selfproject.cordsapp.ui.theme.white

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    darkTheme: Boolean = isSystemInDarkTheme()
) {
    val tabTitles = listOf("Locate", "Archive")
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { tabTitles.count() })
    val unselectedColor = if (darkTheme) white else lightBlack
    val tabNavController = rememberNavController()
    val backStackState = tabNavController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }
    selectedItem = when (backStackState?.destination?.route) {
        Route.LocateScreen.route -> 0
        Route.ArchiveScreen.route -> 1
        else -> 0
    }


    Column(modifier = modifier) {
        TabRow(
            selectedTabIndex = selectedItem
        ) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = index == selectedItem,
                    onClick = {
                        when (index) {
                            0 -> navigateToTab(
                                navController = tabNavController,
                                route = Route.LocateScreen.route
                            )

                            1 -> navigateToTab(
                                navController = tabNavController,
                                route = Route.ArchiveScreen.route
                            )
                        }
                    },
                    text = { Text(text = title) },
                    unselectedContentColor = unselectedColor
                )
            }
        }
        TabNavGraph(tabNavController = tabNavController, homeNavController = navController)
    }
}

private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { screen_route ->
            popUpTo(screen_route) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}


//@Preview(showBackground = true)
//@Composable
//fun LightModePreview() {
//    CordsAppTheme(darkTheme = false) {
//        HomeScreen()
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DarkModePreview() {
//    CordsAppTheme(darkTheme = true) {
//        HomeScreen(darkTheme = true)
//    }
//}