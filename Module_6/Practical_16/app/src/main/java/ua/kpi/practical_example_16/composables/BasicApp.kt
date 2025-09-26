package ua.kpi.practical_example_16.composables

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import ua.kpi.practical_example_16.data.EnergyStation


@Composable
fun BasicApp() {
    // Отримуємо контекст активності для доступу до системних функцій
    val context = LocalContext.current
    
    // Ініціалізуємо клієнт для отримання геолокації
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    // Мокований список енергетичних станцій (для демонстрації)
    val stations = listOf(
        EnergyStation("СЕС 1", 50.4501, 30.5234),
        EnergyStation("СЕС 2", 50.4547, 30.5238),
        EnergyStation("СЕС 3", 50.4495, 30.5210)
    )

    // Стейт для зберігання поточної позиції користувача
    var userLocation by remember { mutableStateOf(LatLng(50.4501, 30.6234)) }

    // Стейт для відстеження наявності дозволу на геолокацію
    var hasLocationPermission by remember { mutableStateOf(false) }

    // Створюємо лаунчер для запиту дозволу на доступ до геолокації
    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        hasLocationPermission = isGranted
        if (isGranted) {
            // Якщо дозвіл надано, отримуємо поточну локацію користувача
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                location?.let {
                    userLocation = LatLng(it.latitude, it.longitude)
                }
            }
        }
    }

    // Запит дозволу на геолокацію при старті компоненти
    LaunchedEffect(Unit) {
        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) -> hasLocationPermission = true  // Якщо дозвіл вже надано
            else -> locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)  // Запит дозволу
        }
    }

    // Ініціалізуємо стан камери карти з початковим центром на позицію користувача
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(userLocation, 12f)
    }

    // Відображаємо Google Map з використанням Compose
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        uiSettings = MapUiSettings(zoomControlsEnabled = true)  // Увімкнути елементи керування масштабом
    ) {
        // Відображаємо маркери для всіх станцій зі списку
        stations.forEach { station ->
            Marker(
                state = rememberMarkerState(position = LatLng(station.latitude, station.longitude)),
                title = station.name  // Назва станції як заголовок маркера
            )
        }

        // Відображаємо маркер поточної позиції користувача, якщо дозвіл надано
        if (hasLocationPermission) {
            Marker(
                state = rememberMarkerState(position = LatLng(userLocation.latitude, userLocation.longitude)),
                title = "Ви тут"  // Текст підпису для маркера користувача
            )
        }
    }
}