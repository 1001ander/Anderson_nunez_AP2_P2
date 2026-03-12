package edu.ucne.anderson_nunez_ap2_p2.domain.usecase

import edu.ucne.anderson_nunez_ap2_p2.domain.repository.JugadorRepository
import javax.inject.Inject

class GetJugadoresUseCase @Inject constructor(
    private val repository: JugadorRepository
) {
    operator fun invoke() = repository.getJugadores()
}
