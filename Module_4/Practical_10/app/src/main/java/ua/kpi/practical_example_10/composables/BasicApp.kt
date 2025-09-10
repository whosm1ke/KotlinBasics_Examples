package ua.kpi.practical_example_10.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ua.kpi.practical_example_10.basic.RetrofitClient
import ua.kpi.practical_example_10.basic.Station

@Composable
fun BasicApp() {
    var stations by remember { mutableStateOf<List<Station>>(emptyList()) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    // --- Завантаження станцій при старті ---
    LaunchedEffect(Unit) {
        loadStations(
            onSuccess = { stations = it },
            onError = { errorMessage = it },
            setLoading = { isLoading = it }
        )
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            // --- Кнопка додати станцію ---
            Button(
                onClick = {
                    scope.launch {
                        try {
                            isLoading = true
                            val newStation = Station(
                                id = (stations.maxOfOrNull { it.id } ?: 0) + 1,
                                name = "NewStation${stations.size + 1}",
                                location = "Kyiv",
                                capacityKw = 50.0
                            )
                            RetrofitClient.api.addStation(newStation)
                            stations = stations + newStation // додаємо локально
                        } catch (e: Exception) {
                            errorMessage = "❌ Не вдалося додати станцію: ${e.localizedMessage}"
                        } finally {
                            isLoading = false
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            ) {
                Text("➕ Додати станцію")
            }

            // --- Відображення ---
            when {
                isLoading -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
                errorMessage != null -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(errorMessage ?: "Невідома помилка", color = MaterialTheme.colorScheme.error)
                }
                else -> LazyColumn {
                    items(stations) { station ->
                        StationItem(station)
                    }
                }
            }
        }
    }
}

// --- Функція для завантаження станцій ---
private suspend fun loadStations(
    onSuccess: (List<Station>) -> Unit,
    onError: (String) -> Unit,
    setLoading: (Boolean) -> Unit
) {
    setLoading(true)
    try {
        val response = RetrofitClient.api.getStations()
        if (response.isNotEmpty()) onSuccess(response)
        else onError("⚠️ Список станцій пустий")
    } catch (e: Exception) {
        onError("❌ Помилка завантаження: ${e.localizedMessage}")
    } finally {
        setLoading(false)
    }
}

// ----------------------------
// Station item (рядок списку)
// ----------------------------
@Composable
fun StationItem(station: Station) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text("🌞 ${station.name}", style = MaterialTheme.typography.titleMedium)
            Text("📍 ${station.location}")
            Text("⚡ Потужність: ${station.capacityKw} кВт")
        }
    }
}