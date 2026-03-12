package edu.ucne.anderson_nunez_ap2_p2.presentation.list

import edu.ucne.anderson_nunez_ap2_p2.domain.model.Jugador

data class ListJugadoresUiState(
    val isLoading: Boolean = false,
    val jugadores: List<Jugador> = emptyList(),
    val error: String? = null
)