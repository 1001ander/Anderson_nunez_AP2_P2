package edu.ucne.anderson_nunez_ap2_p2.presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import edu.ucne.anderson_nunez_ap2_p2.domain.model.Jugador

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    viewModel: ListJugadoresViewModel = hiltViewModel(),
    onNavigateToCreate: () -> Unit = {},
    onNavigateToEdit: (Int) -> Unit = {}
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycleState by lifecycleOwner.lifecycle.currentStateFlow.collectAsStateWithLifecycle()

    LaunchedEffect(lifecycleState) {
        if (lifecycleState == Lifecycle.State.RESUMED) {
            viewModel.onEvent(ListJugadoresEvent.GetJugadores)
        }
    }

    ListBodyScreen(
        state = state,
        onNavigateToCreate = onNavigateToCreate,
        onNavigateToEdit = onNavigateToEdit
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListBodyScreen(
    state: ListJugadoresUiState,
    onNavigateToCreate: () -> Unit = {},
    onNavigateToEdit: (Int) -> Unit = {}
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Jugadores Snake") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToCreate) {
                Icon(Icons.Filled.Add, contentDescription = "Agregar")
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize()) {

            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }

            state.error?.let {
                Text(it, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(16.dp))
            }

            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(state.jugadores) { jugador ->
                    JugadorItem(
                        jugador = jugador,
                        onClick = { onNavigateToEdit(jugador.jugadorId) }
                    )
                }
            }

            Text(
                text = "Total jugadores: ${state.jugadores.size}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Composable
fun JugadorItem(jugador: Jugador, onClick: () -> Unit) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth().clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("ID: ${jugador.jugadorId}", style = MaterialTheme.typography.bodySmall)
            Text(jugador.nombres, style = MaterialTheme.typography.titleMedium)
            Text(jugador.email, style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListBodyScreenPreview() {
    ListBodyScreen(
        state = ListJugadoresUiState(
            jugadores = listOf(
                Jugador(1, "Anderson Nunez", "anderson@gmail.com"),
                Jugador(2, "Andy", "AndyVladimir@gmail.com")
            )
        )
    )
}