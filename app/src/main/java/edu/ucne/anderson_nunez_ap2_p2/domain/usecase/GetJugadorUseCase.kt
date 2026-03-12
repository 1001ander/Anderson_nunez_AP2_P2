package edu.ucne.anderson_nunez_ap2_p2.domain.usecase

import edu.ucne.anderson_nunez_ap2_p2.domain.repository.JugadorRepository
import javax.inject.Inject

class GetJugadorUseCase @Inject constructor(
    private val repository: JugadorRepository
) {
    operator fun invoke(id: Int) = repository.getJugador(id)
}