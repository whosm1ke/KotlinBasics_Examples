package ua.kpi.practical_example_27

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import ua.kpi.practical_example_27.screens.LoginScreen
import ua.kpi.practical_example_27.screens.MainMenuScreen
import ua.kpi.practical_example_27.viewModel.ForecastViewModel

@Composable
fun SolarForecastApp(viewModel: ForecastViewModel) {
    val isLoggedIn by viewModel.isLoggedIn.collectAsState()
    Surface(modifier = Modifier.fillMaxSize()) {
        if (!isLoggedIn) {
            LoginScreen(viewModel)
        } else {
            MainMenuScreen(viewModel)
        }
    }
}