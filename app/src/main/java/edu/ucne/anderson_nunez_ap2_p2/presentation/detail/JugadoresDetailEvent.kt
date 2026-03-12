package edu.ucne.anderson_nunez_ap2_p2.presentation.detail

sealed interface JugadoresDetailEvent {
    data class NombresChanged(val nombres: String) : JugadoresDetailEvent
    data class EmailChanged(val email: String) : JugadoresDetailEvent
    data object Save : JugadoresDetailEvent
}