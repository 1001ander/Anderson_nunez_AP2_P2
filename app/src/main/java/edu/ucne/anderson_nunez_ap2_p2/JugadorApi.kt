package edu.ucne.anderson_nunez_ap2_p2

import edu.ucne.anderson_nunez_ap2_p2.data.remote.dto.JugadorRequest
import edu.ucne.anderson_nunez_ap2_p2.data.remote.dto.JugadorResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface JugadorApi {

    @GET("Jugadores")
    suspend fun getJugadores(): Response<List<JugadorResponseDto>>

    @GET("Jugadores/{id}")
    suspend fun getJugador(
        @Path("id") id: Int
    ): Response<JugadorResponseDto>

    @POST("Jugadores")
    suspend fun createJugador(
        @Body jugador: JugadorRequest
    ): Response<JugadorResponseDto>

    @PUT("Jugadores/{id}")
    suspend fun updateJugador(
        @Path("id") id: Int,
        @Body jugador: JugadorRequest
    ): Response<JugadorResponseDto>
}