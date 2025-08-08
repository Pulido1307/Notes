package com.antonio.pulido.notes.view.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.antonio.pulido.notes.view.core.navigation.Screens.DetailScreen
import com.antonio.pulido.notes.view.core.navigation.Screens.HomeScreen
import com.antonio.pulido.notes.view.notes.detail.DetailScreen
import com.antonio.pulido.notes.view.notes.home.HomeScreen

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = HomeScreen.route
    ) {
        composable(HomeScreen.route) {
            HomeScreen()
        }

        composable(DetailScreen.route) {
            DetailScreen()
        }
    }
}