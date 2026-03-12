package edu.ucne.anderson_nunez_ap2_p2.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object List : Screen()

    @Serializable
    data class Detail(val id: Int = 0) : Screen()
}