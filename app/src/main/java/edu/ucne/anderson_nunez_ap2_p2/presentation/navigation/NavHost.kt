package edu.ucne.anderson_nunez_ap2_p2.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.ucne.anderson_nunez_ap2_p2.presentation.detail.JugadoresDetailScreen
import edu.ucne.anderson_nunez_ap2_p2.presentation.list.ListScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.List) {
        composable<Screen.List> {
            ListScreen(
                onNavigateToCreate = { navController.navigate(Screen.Detail(0)) },
                onNavigateToEdit = { id -> navController.navigate(Screen.Detail(id)) }
            )
        }
        composable<Screen.Detail> {
            JugadoresDetailScreen(onBack = { navController.popBackStack() })
        }
    }
}