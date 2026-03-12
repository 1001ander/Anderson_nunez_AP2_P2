package edu.ucne.anderson_nunez_ap2_p2.data.remote.remotedatasource

import edu.ucne.anderson_nunez_ap2_p2.JugadorApi
import edu.ucne.anderson_nunez_ap2_p2.data.remote.dto.JugadorRequest
import edu.ucne.anderson_nunez_ap2_p2.data.remote.dto.JugadorResponseDto
import retrofit2.HttpException
import javax.inject.Inject

class JugadorRemoteDataSource @Inject constructor(
    private val api: JugadorApi
) {
    suspend fun getJugadores(): Result<List<JugadorResponseDto>> {
        return try {
            val JugadorResponseDto = api.getJugador()
            if (!JugadorResponseDto.isSuccessful)
                Result.failure(Exception("Error de red ${JugadorResponseDto.code()}"))
            else
                Result.success(JugadorResponseDto.body() ?: emptyList())
        } catch (e: HttpException) {
            Result.failure(Exception("Error de servidor", e))
        } catch (e: Exception) {
            Result.failure(Exception("Error desconocido", e))
        }
    }

    suspend fun getJugador(id: Int): Result<JugadorResponse> {
        return try {
            val response = api.getJugador(id)
            if (!response.isSuccessful)
                Result.failure(Exception("Error de red ${response.code()}"))
            else
                Result.success(response.body()!!)
        } catch (e: HttpException) {
            Result.failure(Exception("Error de servidor", e))
        } catch (e: Exception) {
            Result.failure(Exception("Error desconocido", e))
        }
    }

    suspend fun createJugador(jugador: JugadorRequest): Result<JugadorResponse> {
        return try {
            val response = api.createJugador(jugador)
            if (!response.isSuccessful)
                Result.failure(Exception("Error de red ${response.code()}"))
            else
                Result.success(response.body()!!)
        } catch (e: HttpException) {
            Result.failure(Exception("Error de servidor", e))
        } catch (e: Exception) {
            Result.failure(Exception("Error desconocido", e))
        }
    }

    suspend fun updateJugador(id: Int, jugador: JugadorRequest): Result<JugadorResponse> {
        return try {
            val response = api.updateJugador(id, jugador)
            if (!response.isSuccessful)
                Result.failure(Exception("Error de red ${response.code()}"))
            else
                Result.success(response.body()!!)
        } catch (e: HttpException) {
            Result.failure(Exception("Error de servidor", e))
        } catch (e: Exception) {
            Result.failure(Exception("Error desconocido", e))
        }
    }
}
