package ua.kpi.practical_example_10.advanced

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ua.kpi.practical_example_10.basic.Station
import ua.kpi.practical_example_10.medium.Forecast
import ua.kpi.practical_example_10.medium.ForecastStats
import ua.kpi.practical_example_10.medium.RetrofitClient

// ----------------------------
// ViewModel для просунутого рівня
// ----------------------------
class SolarViewModel : ViewModel() {

    private val api = RetrofitClient.api

    // Кешування даних у пам’яті
    private val _stationsCache = mutableMapOf<Int, Station>()
    private val _forecastsCache = mutableMapOf<Int, List<Forecast>>()
    private val _statsCache = mutableMapOf<Int, ForecastStats>()

    // StateFlows для UI
    private val _stations = MutableStateFlow<List<Station>>(emptyList())
    val stations: StateFlow<List<Station>> = _stations

    private val _forecasts = MutableStateFlow<Map<Int, List<Forecast>>>(emptyMap())
    val forecasts: StateFlow<Map<Int, List<Forecast>>> = _forecasts

    private val _stats = MutableStateFlow<Map<Int, ForecastStats>>(emptyMap())
    val stats: StateFlow<Map<Int, ForecastStats>> = _stats

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    // --- Повторна спроба запиту з N спроб ---
    private suspend fun <T> retryRequest(times: Int = 3, block: suspend () -> T): T {
        var currentAttempt = 0
        var lastError: Exception? = null
        while (currentAttempt < times) {
            try {
                return block()
            } catch (e: Exception) {
                lastError = e
                currentAttempt++
            }
        }
        throw lastError ?: Exception("Unknown error")
    }

    // --- Завантаження станцій ---
    fun loadStations() {
        viewModelScope.launch {
            _loading.value = true
            try {
                val data = retryRequest { api.getStations() }
                _stations.value = data.sortedBy { it.name }
                // Кешуємо
                data.forEach { _stationsCache[it.id] = it }
            } catch (e: Exception) {
                _error.value = "❌ Error loading stations: ${e.localizedMessage}"
            } finally {
                _loading.value = false
            }
        }
    }

    // --- Завантаження прогнозів станції ---
    fun loadForecasts(stationId: Int) {
        viewModelScope.launch {
            try {
                val data = retryRequest { api.getForecasts(stationId) }
                _forecasts.value = _forecasts.value + (stationId to data.sortedBy { it.date })
                _forecastsCache[stationId] = data
            } catch (e: Exception) {
                _error.value = "❌ Error loading forecasts: ${e.localizedMessage}"
            }
        }
    }

    // --- Завантаження статистики станції ---
    fun loadStats(stationId: Int) {
        viewModelScope.launch {
            try {
                val data = retryRequest { api.getForecastStats(stationId) }
                _stats.value = _stats.value + (stationId to data)
                _statsCache[stationId] = data
            } catch (e: Exception) {
                _error.value = "❌ Error loading stats: ${e.localizedMessage}"
            }
        }
    }

    // --- Генерація прогнозів ---
    fun generateForecasts(stationId: Int, days: Int) {
        viewModelScope.launch {
            try {
                val newForecasts = retryRequest { api.generateForecasts(stationId, days) }
                val updated = (_forecasts.value[stationId] ?: emptyList()) + newForecasts
                _forecasts.value = _forecasts.value + (stationId to updated.sortedBy { it.date })
                _forecastsCache[stationId] = updated
                // Після генерації оновлюємо статистику
                loadStats(stationId)
            } catch (e: Exception) {
                _error.value = "❌ Error generating forecasts: ${e.localizedMessage}"
            }
        }
    }
}
