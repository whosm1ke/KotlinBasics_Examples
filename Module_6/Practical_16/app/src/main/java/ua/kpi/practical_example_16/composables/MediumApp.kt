package ua.kpi.practical_example_16.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
fun MediumApp() {
    // Моковані дані станцій для демонстрації
    val stations = listOf(
        EnergyStation("СЕС 1", 50.4501, 30.5234,"Сонячна", "Потужність 100 кВт", ),
        EnergyStation("СЕС 2", 50.4547, 30.5238,"Сонячна", "Потужність 200 кВт", ),
        EnergyStation("СЕС 3", 50.4495, 30.5210,"Сонячна", "Потужність 150 кВт", )
    )

    // Стейт для відстеження обраної станції (використовується для підсвітки у списку та фокусу на карті)
    var selectedStation by remember { mutableStateOf<EnergyStation?>(null) }

    // Створення стану камери карти з початковою позицією Києва
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(50.4501, 30.5234), 12f)
    }

    // Основний макет за допомогою Row (два стовпці: список станцій і карта)
    Row(modifier = Modifier.fillMaxSize()) {

        // Список станцій зліва (30% ширини)
        LazyColumn(
            modifier = Modifier
                .weight(0.3f)
                .fillMaxHeight()
                .background(Color(0xFFEFEFEF))
        ) {
            items(stations) { station ->
                // Перевірка, чи є ця станція обраною
                val isSelected = station == selectedStation
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .clickable {
                            // При натисканні на станцію:
                            // 1. Встановлюємо її як обрану
                            selectedStation = station
                            // 2. Переміщуємо камеру на цей маркер з збільшенням масштабу
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
                        Text(station.type, style = MaterialTheme.typography.bodyMedium)
                        Text(station.description, style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }

        // Карта справа (70% ширини)
        GoogleMap(
            modifier = Modifier.weight(0.7f),
            cameraPositionState = cameraPositionState,
            uiSettings = MapUiSettings(zoomControlsEnabled = true)
        ) {
            stations.forEach { station ->
                // Створюємо стан маркера для кожної станції
                val markerState = rememberMarkerState(LatLng(station.latitude, station.longitude))
                Marker(
                    state = markerState,
                    title = station.name,
                    snippet = station.description,
                    onClick = {
                        // При натисканні на маркер:
                        // 1. Встановлюємо цю станцію як обрану
                        selectedStation = station
                        false // Повертаємо false, щоб показати InfoWindow
                    }
                )
            }
        }
    }
}

@Composable
fun rememberMarkerState(position: LatLng) = remember {
    MarkerState(position)
}