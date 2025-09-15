package ua.kpi.practical_example_14.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ua.kpi.practical_example_14.data.SolarStation

@Composable
fun AdvancedApp() {
    // Дані зі станціями
    val stations = remember {
        listOf(
            SolarStation("Сонячна Станція 1", "Сонячна", "Прогнозована потужність 120 кВт", 50.45, 30.52, 120.0),
            SolarStation("Сонячна Станція 2", "Сонячна", "Прогнозована потужність 150 кВт", 50.48, 30.55, 150.0),
            SolarStation("Сонячна Станція 3", "Сонячна", "Прогнозована потужність 200 кВт", 50.50, 30.57, 200.0)
        )
    }

    // Стан для інтерактивності між списком і картою
    var selectedStation by remember { mutableStateOf<SolarStation?>(null) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Сонячні електростанції із картою (симуляція)", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxSize()) {
            // Ліва частина - список
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(stations) { station ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .clickable { if(selectedStation == null) selectedStation = station else selectedStation = null }, // натискання на елемент списку фокусується на "карті"
                        elevation = CardDefaults.cardElevation(4.dp),
                        colors = if (station == selectedStation) CardDefaults.cardColors(containerColor = Color(0xFFE0F7FA))
                        else CardDefaults.cardColors()
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(station.name, fontWeight = FontWeight.Bold)
                            Text(station.type)
                            Text(station.description)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Права частина - "карта" (симуляція)
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Карта (симуляція)", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))

                // Відображення маркерів
                stations.forEach { station ->
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth()
                            .height(40.dp)
                            .clickable { selectedStation = station }, // натискання на маркер виділяє елемент списку
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            station.name,
                            color = if (station == selectedStation) Color.Red else Color.Black,
                            fontWeight = if (station == selectedStation) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                selectedStation?.let {
                    Text("Вибрано: ${it.name}", fontWeight = FontWeight.Bold, color = Color.Blue)
                }
            }
        }
    }
}