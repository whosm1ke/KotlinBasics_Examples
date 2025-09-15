package ua.kpi.practical_example_16.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import ua.kpi.practical_example_16.data.EnergyStation

@Composable
fun AdvancedApp() {
    // Моковані дані станцій
    val stations = remember {
        mutableStateListOf(
            EnergyStation("СЕС 1", 50.4501, 30.5234, "Сонячна", "Потужність 100 кВт", 100, "Активна"),
            EnergyStation("СЕС 2", 50.4547, 30.5238, "Сонячна", "Потужність 200 кВт", 200, "Активна"),
            EnergyStation("СЕС 3", 50.4495, 30.5210, "Сонячна", "Потужність 150 кВт", 150, "На ремонті"),
            EnergyStation("ВЕС 1", 50.4520, 30.5250, "Вітрова", "Потужність 300 кВт", 300, "Активна")
        )
    }

    // Фільтри та сортування
    var selectedType by remember { mutableStateOf("Всі") }
    var sortByPowerAsc by remember { mutableStateOf(true) }

    // Стейт для виділеної станції
    var selectedStation by remember { mutableStateOf<EnergyStation?>(null) }

    // Відфільтрований та відсортований список
    val filteredStations by remember(stations, selectedType, sortByPowerAsc) {
        derivedStateOf {
            stations.filter { selectedType == "Всі" || it.type == selectedType }
                .sortedBy { if (sortByPowerAsc) it.power else -it.power }
        }
    }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(50.4501, 30.5234), 12f)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // Панель фільтрів і сортування
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Тип станції
            FilterDropdown(
                label = "Тип",
                options = listOf("Всі", "Сонячна", "Вітрова"),
                selectedOption = selectedType,
                onOptionSelected = { selectedType = it }
            )
            // Сортування за потужністю
            Button(onClick = { sortByPowerAsc = !sortByPowerAsc }) {
                Text(if (sortByPowerAsc) "Сортувати ↓" else "Сортувати ↑")
            }
        }

        Row(modifier = Modifier.fillMaxSize()) {
            // Список станцій зліва
            LazyColumn(
                modifier = Modifier
                    .weight(0.35f)
                    .fillMaxHeight()
                    .background(Color(0xFFEFEFEF))
            ) {
                items(filteredStations) { station ->
                    val isSelected = station == selectedStation
                    Card(
                        modifier = Modifier
                            .padding(6.dp)
                            .fillMaxWidth()
                            .clickable {
                                selectedStation = station
                                cameraPositionState.position = CameraPosition.fromLatLngZoom(
                                    LatLng(station.latitude, station.longitude), 14f
                                )
                            },
                        colors = CardDefaults.cardColors(
                            containerColor = if (isSelected) Color(0xFFBBDEFB) else Color.White
                        )
                    ) {
                        Column(modifier = Modifier.padding(8.dp)) {
                            Text(station.name, style = MaterialTheme.typography.titleMedium)
                            Text("${station.type} • ${station.status}", style = MaterialTheme.typography.bodyMedium)
                            Text("Потужність: ${station.power} кВт", style = MaterialTheme.typography.bodySmall)
                            Text(station.description, style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }

            // Карта справа
            GoogleMap(
                modifier = Modifier.weight(0.65f),
                cameraPositionState = cameraPositionState,
                uiSettings = MapUiSettings(zoomControlsEnabled = true)
            ) {
                filteredStations.forEach { station ->
                    val markerState = rememberMarkerState(LatLng(station.latitude, station.longitude))
                    Marker(
                        state = markerState,
                        title = station.name,
                        snippet = "${station.type} • ${station.status}\nПотужність: ${station.power} кВт",
                        onClick = {
                            selectedStation = station
                            false // false = показати InfoWindow
                        }
                    )
                }
            }
        }
    }
}

/**
 * Компонент Dropdown для фільтру
 */
@Composable
fun FilterDropdown(
    label: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Box {
        Button(onClick = { expanded = true }) {
            Text("$label: $selectedOption")
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}

