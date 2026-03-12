package edu.ucne.anderson_nunez_ap2_p2.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.anderson_nunez_ap2_p2.domain.usecase.GetJugadoresUseCase
import edu.ucne.anderson_nunez_ap2_p2.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListJugadoresViewModel @Inject constructor(
    private val getJugadoresUseCase: GetJugadoresUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ListJugadoresUiState())
    val state = _state.asStateFlow()

    init { getJugadores() }

    fun onEvent(event: ListJugadoresEvent) {
        when (event) {
            ListJugadoresEvent.GetJugadores -> getJugadores()
        }
    }

    private fun getJugadores() {
        viewModelScope.launch {
            getJugadoresUseCase().collect { result ->
                when (result) {
                    is Resource.Loading -> _state.update { it.copy(isLoading = true) }
                    is Resource.Success -> _state.update {
                        it.copy(isLoading = false, jugadores = result.data ?: emptyList())
                    }
                    is Resource.Error -> _state.update {
                        it.copy(isLoading = false, error = result.message)
                    }
                }
            }
        }
    }
}