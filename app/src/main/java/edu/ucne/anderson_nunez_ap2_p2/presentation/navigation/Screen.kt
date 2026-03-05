package edu.ucne.anderson_nunez_ap2_p2.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object List : Screen()

    @Serializable
    data object Detail : Screen()
}