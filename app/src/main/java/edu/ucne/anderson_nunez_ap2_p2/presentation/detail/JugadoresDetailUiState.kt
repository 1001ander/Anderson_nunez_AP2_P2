package edu.ucne.anderson_nunez_ap2_p2.presentation.detail

data class DetailUiState(
    val isLoading: Boolean = false,
    val jugadorId: Int = 0,
    val nombres: String = "",
    val email: String = "",
    val error: String? = null,
    val nombresError: String? = null,
    val emailError: String? = null,
    val isSaved: Boolean = false
)