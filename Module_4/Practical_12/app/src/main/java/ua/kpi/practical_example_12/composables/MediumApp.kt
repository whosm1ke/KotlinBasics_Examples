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
import ua.kpi.practical_example_12.medium.ForecastViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediumApp() {
    val context = LocalContext.current
    val viewModel = remember { ForecastViewModel() }
    val snackbarHostState = remember { SnackbarHostState() }

    // Відображення Snackbar при помилках
    LaunchedEffect(viewModel.errorMessage) {
        viewModel.errorMessage?.let { snackbarHostState.showSnackbar(it) }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = { TopAppBar(title = { Text("Сонячна електростанція") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Button(onClick = { viewModel.fetchForecast(context) }) {
                Text("Отримати прогноз на сьогодні")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { viewModel.fetchError(404, context) }) {
                Text("Симулювати помилку 404")
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
