package ua.kpi.practical_example_14.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import ua.kpi.practical_example_14.data.SolarStation


@Composable
fun MediumApp() {
    // Створення списку сонячних станцій з початковими даними
    val stations = remember {
        listOf(
            SolarStation("Сонячна Станція 1", "Сонячна", "Прогнозована потужність 120 кВт", 50.45, 30.52, 120.0),
            SolarStation("Сонячна Станція 2", "Сонячна", "Прогнозована потужність 150 кВт", 50.48, 30.55, 150.0),
            SolarStation("Сонячна Станція 3", "Сонячна", "Прогнозована потужність 200 кВт", 50.50, 30.57, 200.0)
        )
    }

    // Стан для відстеження обраної станції (null означає, що ще не обрано)
    var selectedStation by remember { mutableStateOf<SolarStation?>(null) }

    // Якщо станція не вибрана, показуємо список карток
    if (selectedStation == null) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            // Заголовок списку
            Text(
                "Список сонячних електростанцій у картках",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Використовуємо LazyColumn для відображення списку станцій
            LazyColumn {
                items(stations) { station ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .clickable { selectedStation = station }, // При натисканні переходить до деталей
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(station.name, fontWeight = FontWeight.Bold)
                            Text(station.type)
                            Text(station.description)
                        }
                    }
                }
            }
        }
    } else {
        // Якщо станція вибрана, показуємо детальну інформацію
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Заголовок детального відображення
            Text("Детальна інформація", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            // Відображення даних обраної станції
            Text("Назва: ${selectedStation!!.name}", fontWeight = FontWeight.Bold)
            Text("Тип: ${selectedStation!!.type}")
            Text("Опис: ${selectedStation!!.description}")
            Text("Прогнозована потужність: ${selectedStation!!.forecastPower} кВт")
            Spacer(modifier = Modifier.height(16.dp))
            // Кнопка для повернення до списку
            Button(onClick = { selectedStation = null }) {
                Text("Назад")
            }
        }
    }
}