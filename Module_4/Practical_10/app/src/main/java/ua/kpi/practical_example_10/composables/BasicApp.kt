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
    // --- Стан для зберігання списку станцій ---
    var stations by remember { mutableStateOf<List<Station>>(emptyList()) }
    
    // --- Стан для повідомлення про помилку ---
    var errorMessage by remember { mutableStateOf<String?>(null) }
    
    // --- Стан для індикатора завантаження ---
    var isLoading by remember { mutableStateOf(false) }

    // --- Отримання корутинного скоупу для запуску асинхронних операцій ---
    val scope = rememberCoroutineScope()

    // --- Завантаження даних при старті компоненти ---
    LaunchedEffect(Unit) {
        loadStations(
            onSuccess = { stations = it },  // Якщо успішно, оновлюємо стан зі списком станцій
            onError = { errorMessage = it },  // Якщо помилка, встановлюємо повідомлення
            setLoading = { isLoading = it }   // Встановлюємо стан завантаження
        )
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            // --- Кнопка для додавання нової станції ---
            Button(
                onClick = {
                    scope.launch {
                        try {
                            isLoading = true  // Включаємо індикатор завантаження
                            
                            // Створюємо нову станцію з унікальним ID та ім'ям
                            val newStation = Station(
                                id = (stations.maxOfOrNull { it.id } ?: 0) + 1,
                                name = "NewStation${stations.size + 1}",
                                location = "Kyiv",
                                capacityKw = 50.0
                            )
                            
                            // Викликаємо API для додавання станції
                            RetrofitClient.api.addStation(newStation)
                            
                            // Додаємо нову станцію до локального списку
                            stations = stations + newStation
                        } catch (e: Exception) {
                            errorMessage = "❌ Не вдалося додати станцію: ${e.localizedMessage}"
                        } finally {
                            isLoading = false  // Вимикаємо індикатор завантаження
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            ) {
                Text("➕ Додати станцію")
            }

            // --- Відображення відповідно до стану ---
            when {
                isLoading -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()  // Показуємо індикатор завантаження
                }
                errorMessage != null -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(errorMessage ?: "Невідома помилка", color = MaterialTheme.colorScheme.error)  // Показуємо помилку
                }
                else -> LazyColumn {
                    items(stations) { station ->
                        StationItem(station)  // Відображаємо кожну станцію
                    }
                }
            }
        }
    }
}

// --- Функція для завантаження списку станцій з API ---
private suspend fun loadStations(
    onSuccess: (List<Station>) -> Unit,  // Функція, що викликається при успішному завантаженні
    onError: (String) -> Unit,          // Функція, що викликається при помилці
    setLoading: (Boolean) -> Unit        // Функція для зміни стану завантаження
) {
    setLoading(true)  // Включаємо індикатор завантаження
    try {
        val response = RetrofitClient.api.getStations()  // Запит до API
        if (response.isNotEmpty()) onSuccess(response)     // Якщо є дані, передаємо їх
        else onError("⚠️ Список станцій пустий")         // Інакше виводимо повідомлення
    } catch (e: Exception) {
        onError("❌ Помилка завантаження: ${e.localizedMessage}")  // Обробляємо помилку
    } finally {
        setLoading(false)  // Вимикаємо індикатор завантаження
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
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)  // Встановлюємо тінь для карти
    ) {
        Column(Modifier.padding(16.dp)) {
            Text("🌞 ${station.name}", style = MaterialTheme.typography.titleMedium)  // Назва станції
            Text("📍 ${station.location}")  // Локація
            Text("⚡ Потужність: ${station.capacityKw} кВт")  // Потужність
        }
    }
}