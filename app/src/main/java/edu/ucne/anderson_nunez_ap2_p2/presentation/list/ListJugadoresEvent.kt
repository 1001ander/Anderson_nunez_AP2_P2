package edu.ucne.anderson_nunez_ap2_p2.presentation.list

sealed interface ListJugadoresEvent {
    data object GetJugadores : ListJugadoresEvent
}

