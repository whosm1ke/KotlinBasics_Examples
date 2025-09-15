package ua.kpi.practical_example_12.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import ua.kpi.practical_example_12.advanced.ForecastViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdvancedApp() {
    val context = LocalContext.current
    val viewModel = remember { ForecastViewModel(context) }
    val snackbarHostState = remember { SnackbarHostState() }

    // Показ Snackbar при помилках
    LaunchedEffect(viewModel.errorMessage) {
        viewModel.errorMessage?.let { snackbarHostState.showSnackbar(it) }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = { TopAppBar(title = { Text("Сонячна електростанція - Advanced") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Button(onClick = { viewModel.fetchForecastWithRetry() }) {
                Text("Отримати прогноз на сьогодні (з retry)")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { viewModel.fetchError(500) }) {
                Text("Симулювати помилку 500")
            }

            Spacer(modifier = Modifier.height(20.dp))

            viewModel.forecast?.let { f ->
                Text("Дата: ${f.date}")
                Text("Потужність: ${f.powerKwh} кВт·год")
                Text("Статус: ${f.status}")
            }
        }
    }

}
