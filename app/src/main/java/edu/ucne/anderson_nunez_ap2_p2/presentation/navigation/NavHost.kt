package edu.ucne.anderson_nunez_ap2_p2.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.ucne.anderson_nunez_ap2_p2.presentation.detail.BorrarDetailScreen
import edu.ucne.anderson_nunez_ap2_p2.presentation.list.BorrarListScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.List
    ) {
        composable<Screen.List> {
            BorrarListScreen(
                onNavigateToDetail = { navController.navigate(Screen.Detail) }
            )
        }
        composable<Screen.Detail> {
            BorrarDetailScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}