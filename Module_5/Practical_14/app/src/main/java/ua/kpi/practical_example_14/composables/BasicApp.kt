package ua.kpi.practical_example_14.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ua.kpi.practical_example_14.data.SolarStation


@Composable
fun BasicApp() {
    // Приклад даних
    val stations = remember {
        listOf(
            SolarStation("Сонячна Станція 1", "Сонячна", "", 50.45, 30.52, 120.0),
            SolarStation("Сонячна Станція 2", "Сонячна", "", 50.48, 30.55, 150.0),
            SolarStation("Сонячна Станція 3", "Сонячна", "", 50.50, 30.57, 200.0),
            SolarStation("Сонячна Станція 4", "Сонячна", "", 50.50, 30.57, 200.0),
            SolarStation("Сонячна Станція 5", "Сонячна", "", 50.50, 30.57, 200.0),
            SolarStation("Сонячна Станція 6", "Сонячна", "", 50.50, 30.57, 200.0),
            SolarStation("Сонячна Станція 7", "Сонячна", "", 50.50, 30.57, 200.0),
            SolarStation("Сонячна Станція 8", "Сонячна", "", 50.50, 30.57, 200.0),
            SolarStation("Сонячна Станція 9", "Сонячна", "", 50.50, 30.57, 200.0),
        )
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            "Список сонячних електростанцій",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Динамічний список за допомогою LazyColumn
        LazyColumn {
            items(stations) { station ->
                Text(
                    "${station.name} - ${station.type}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
