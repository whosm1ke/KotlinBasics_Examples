package ua.kpi.practical_example_10.medium

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ua.kpi.practical_example_10.basic.Station

class SolarViewModel : ViewModel() {

    private val api = RetrofitClient.api

    private val _stations = MutableStateFlow<List<Station>>(emptyList())
    val stations: StateFlow<List<Station>> = _stations

    private val _forecasts = MutableStateFlow<Map<Int, List<Forecast>>>(emptyMap())
    val forecasts: StateFlow<Map<Int, List<Forecast>>> = _forecasts

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    // --- Завантаження станцій ---
    fun loadStations() {
        viewModelScope.launch {
            _loading.value = true
            try {
                val data = api.getStations()
                _stations.value = data.sortedBy { it.name } // приклад сортування
            } catch (e: Exception) {
                _error.value = "❌ Error loading stations: ${e.localizedMessage}"
            } finally {
                _loading.value = false
            }
        }
    }

    // --- Завантаження прогнозів для станції ---
    fun loadForecasts(stationId: Int) {
        viewModelScope.launch {
            try {
                val data = api.getForecasts(stationId)
                _forecasts.value = _forecasts.value + (stationId to data.sortedBy { it.date })
            } catch (e: Exception) {
                _error.value = "❌ Error loading forecasts: ${e.localizedMessage}"
            }
        }
    }

    // --- Генерація прогнозів ---
    fun generateForecasts(stationId: Int, days: Int) {
        viewModelScope.launch {
            try {
                val newForecasts = api.generateForecasts(stationId, days)
                val updated = (_forecasts.value[stationId] ?: emptyList()) + newForecasts
                _forecasts.value = _forecasts.value + (stationId to updated.sortedBy { it.date })
            } catch (e: Exception) {
                _error.value = "❌ Error generating forecasts: ${e.localizedMessage}"
            }
        }
    }
}