package edu.ucne.anderson_nunez_ap2_p2.data.repository

import edu.ucne.anderson_nunez_ap2_p2.data.remote.dto.JugadorRequest
import edu.ucne.anderson_nunez_ap2_p2.data.remote.remotedatasource.JugadorRemoteDataSource
import edu.ucne.anderson_nunez_ap2_p2.domain.model.Jugador
import edu.ucne.anderson_nunez_ap2_p2.domain.repository.JugadorRepository
import edu.ucne.anderson_nunez_ap2_p2.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class JugadorRepositoryImpl @Inject constructor(
    private val remoteDataSource: JugadorRemoteDataSource
) : JugadorRepository {

    override fun getJugadores(): Flow<Resource<List<Jugador>>> = flow {
        emit(Resource.Loading())
        remoteDataSource.getJugadores()
            .onSuccess { emit(Resource.Success(it.map { dto -> dto.toDomain() })) }
            .onFailure { emit(Resource.Error(it.message ?: "Error desconocido")) }
    }

    override fun getJugador(id: Int): Flow<Resource<Jugador>> = flow {
        emit(Resource.Loading())
        remoteDataSource.getJugador(id)
            .onSuccess { emit(Resource.Success(it.toDomain())) }
            .onFailure { emit(Resource.Error(it.message ?: "Error desconocido")) }
    }

    override fun createJugador(jugador: Jugador): Flow<Resource<Jugador>> = flow {
        emit(Resource.Loading())
        val request = JugadorRequest(nombres = jugador.nombres, email = jugador.email)
        remoteDataSource.createJugador(request)
            .onSuccess { emit(Resource.Success(it.toDomain())) }
            .onFailure { emit(Resource.Error(it.message ?: "Error desconocido")) }
    }

    override fun updateJugador(id: Int, jugador: Jugador): Flow<Resource<Jugador>> = flow {
        emit(Resource.Loading())
        val request = JugadorRequest(nombres = jugador.nombres, email = jugador.email)
        remoteDataSource.updateJugador(id, request)
            .onSuccess { emit(Resource.Success(it.toDomain())) }
            .onFailure { emit(Resource.Error(it.message ?: "Error desconocido")) }
    }
}