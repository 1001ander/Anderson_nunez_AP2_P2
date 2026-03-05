package edu.ucne.anderson_nunez_ap2_p2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import edu.ucne.anderson_nunez_ap2_p2.presentation.navigation.AppNavHost
import edu.ucne.anderson_nunez_ap2_p2.ui.theme.Anderson_nunez_AP2_P2Theme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Anderson_nunez_AP2_P2Theme {
                AppNavHost()
            }
        }
    }
}