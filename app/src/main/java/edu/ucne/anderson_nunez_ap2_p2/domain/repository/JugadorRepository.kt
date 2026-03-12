package edu.ucne.anderson_nunez_ap2_p2.domain.repository

import edu.ucne.anderson_nunez_ap2_p2.domain.model.Jugador
import edu.ucne.anderson_nunez_ap2_p2.util.Resource
import kotlinx.coroutines.flow.Flow

interface JugadorRepository {
    fun getJugadores(): Flow<Resource<List<Jugador>>>
    fun getJugador(id: Int): Flow<Resource<Jugador>>
    fun createJugador(jugador: Jugador): Flow<Resource<Jugador>>
    fun updateJugador(id: Int, jugador: Jugador): Flow<Resource<Jugador>>
}
