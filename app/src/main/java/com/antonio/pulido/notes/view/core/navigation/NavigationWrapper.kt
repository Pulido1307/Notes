package com.antonio.pulido.notes.view.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
            HomeScreen(
                navToDetailNote = {
                    navController.navigate(DetailScreen.createRoute(it))
                }
            )
        }

        composable(
            route = Screens.DetailScreen.route,
            arguments = listOf(
                navArgument("noteId") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getInt("noteId")
            DetailScreen(
                onBack = {
                    navController.popBackStack()
                },
                noteId = noteId ?: -1
            )
        }
    }
}