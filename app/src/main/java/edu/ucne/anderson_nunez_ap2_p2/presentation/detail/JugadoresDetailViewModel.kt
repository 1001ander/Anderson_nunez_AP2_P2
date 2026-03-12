package edu.ucne.anderson_nunez_ap2_p2.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.anderson_nunez_ap2_p2.domain.model.Jugador
import edu.ucne.anderson_nunez_ap2_p2.domain.usecase.CreateJugadorUseCase
import edu.ucne.anderson_nunez_ap2_p2.domain.usecase.GetJugadorUseCase
import edu.ucne.anderson_nunez_ap2_p2.domain.usecase.UpdateJugadorUseCase
import edu.ucne.anderson_nunez_ap2_p2.presentation.navigation.Screen
import edu.ucne.anderson_nunez_ap2_p2.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getJugadorUseCase: GetJugadorUseCase,
    private val createJugadorUseCase: CreateJugadorUseCase,
    private val updateJugadorUseCase: UpdateJugadorUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(DetailUiState())
    val state = _state.asStateFlow()

    init {
        val args = savedStateHandle.toRoute<Screen.Detail>()
        if (args.id != 0) loadJugador(args.id)
    }

    fun onEvent(event: JugadoresDetailEvent) {
        when (event) {
            is JugadoresDetailEvent.NombresChanged -> _state.update {
                it.copy(nombres = event.nombres, nombresError = null)
            }
            is JugadoresDetailEvent.EmailChanged -> _state.update {
                it.copy(email = event.email, emailError = null)
            }
            JugadoresDetailEvent.Save -> save()
        }
    }

    private fun loadJugador(id: Int) {
        viewModelScope.launch {
            getJugadorUseCase(id).collect { result ->
                when (result) {
                    is Resource.Loading -> _state.update { it.copy(isLoading = true) }
                    is Resource.Success -> _state.update {
                        it.copy(
                            isLoading = false,
                            jugadorId = result.data?.jugadorId ?: 0,
                            nombres = result.data?.nombres ?: "",
                            email = result.data?.email ?: ""
                        )
                    }
                    is Resource.Error -> _state.update {
                        it.copy(isLoading = false, error = result.message)
                    }
                }
            }
        }
    }

    private fun save() {
        val current = _state.value


        var hasError = false
        if (current.nombres.isBlank()) {
            _state.update { it.copy(nombresError = "El nombre es obligatorio") }
            hasError = true
        }
        if (current.email.isBlank() || !current.email.contains("@")) {
            _state.update { it.copy(emailError = "Email inválido, debe contener @") }
            hasError = true
        }
        if (hasError) return

        viewModelScope.launch {
            val jugador = Jugador(
                jugadorId = current.jugadorId,
                nombres = current.nombres,
                email = current.email
            )
            val flow = if (current.jugadorId == 0)
                createJugadorUseCase(jugador)
            else
                updateJugadorUseCase(current.jugadorId, jugador)

            flow.collect { result ->
                when (result) {
                    is Resource.Loading -> _state.update { it.copy(isLoading = true) }
                    is Resource.Success -> _state.update {
                        it.copy(isLoading = false, isSaved = true)
                    }
                    is Resource.Error -> _state.update {
                        it.copy(isLoading = false, error = result.message)
                    }
                }
            }
        }
    }
}