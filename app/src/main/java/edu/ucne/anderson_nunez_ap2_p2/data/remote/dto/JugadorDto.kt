package edu.ucne.anderson_nunez_ap2_p2.data.remote.dto

import edu.ucne.anderson_nunez_ap2_p2.domain.model.Jugador

data class JugadorRequest(
    val nombres: String = "",
    val email: String = ""
)

data class JugadorResponseDto(
    val jugadorId: Int = 0,
    val nombres: String = "",
    val email: String = ""
) {
    fun toDomain() = Jugador(
        jugadorId = jugadorId,
        nombres = nombres,
        email = email,

    )
}