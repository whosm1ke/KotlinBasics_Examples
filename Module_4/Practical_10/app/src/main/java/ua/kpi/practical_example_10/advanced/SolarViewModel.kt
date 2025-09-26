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

    // Ініціалізація API клієнта через Retrofit
    private val api = RetrofitClient.api

    // Кешування даних у пам’яті для уникнення повторних запитів
    private val _stationsCache = mutableMapOf<Int, Station>()
    private val _forecastsCache = mutableMapOf<Int, List<Forecast>>()
    private val _statsCache = mutableMapOf<Int, ForecastStats>()

    // StateFlows для спостереження змін у UI
    private val _stations = MutableStateFlow<List<Station>>(emptyList())
    val stations: StateFlow<List<Station>> = _stations

    private val _forecasts = MutableStateFlow<Map<Int, List<Forecast>>>(emptyMap())
    val forecasts: StateFlow<Map<Int, List<Forecast>>> = _forecasts

    private val _stats = MutableStateFlow<Map<Int, ForecastStats>>(emptyMap())
    val stats: StateFlow<Map<Int, ForecastStats>> = _stats

    // Помилки та статус завантаження для UI
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    // --- Повторна спроба запиту з N спроб ---
    // Метод для повторних спроб виконання асинхронної операції з обмеженням кількості спроб
    private suspend fun <T> retryRequest(times: Int = 3, block: suspend () -> T): T {
        var currentAttempt = 0
        var lastError: Exception? = null
        while (currentAttempt < times) {
            try {
                return block() // Виконуємо блок коду
            } catch (e: Exception) {
                lastError = e
                currentAttempt++
            }
        }
        throw lastError ?: Exception("Unknown error") // Якщо всі спроби не вдалося, кидаємо помилку
    }

    // --- Завантаження станцій ---
    // Метод для завантаження списку станцій з API і кешування їх у пам’яті
    fun loadStations() {
        viewModelScope.launch { // Запускаємо в scope ViewModel
            _loading.value = true // Встановлюємо статус завантаження
            try {
                val data = retryRequest { api.getStations() } // Робимо запит з повторними спробами
                _stations.value = data.sortedBy { it.name } // Сортуємо за іменем
                // Кешуємо отримані дані
                data.forEach { _stationsCache[it.id] = it }
            } catch (e: Exception) {
                _error.value = "❌ Error loading stations: ${e.localizedMessage}" // Встановлюємо помилку
            } finally {
                _loading.value = false // Завершення завантаження
            }
        }
    }

    // --- Завантаження прогнозів станції ---
    // Метод для завантаження прогнозів для конкретної станції з кешуванням
    fun loadForecasts(stationId: Int) {
        viewModelScope.launch {
            try {
                val data = retryRequest { api.getForecasts(stationId) }
                _forecasts.value = _forecasts.value + (stationId to data.sortedBy { it.date }) // Додаємо нові дані до списку
                _forecastsCache[stationId] = data // Кешуємо
            } catch (e: Exception) {
                _error.value = "❌ Error loading forecasts: ${e.localizedMessage}"
            }
        }
    }

    // --- Завантаження статистики станції ---
    // Метод для завантаження статистики для конкретної станції з кешуванням
    fun loadStats(stationId: Int) {
        viewModelScope.launch {
            try {
                val data = retryRequest { api.getForecastStats(stationId) }
                _stats.value = _stats.value + (stationId to data) // Оновлюємо статистику
                _statsCache[stationId] = data // Кешуємо
            } catch (e: Exception) {
                _error.value = "❌ Error loading stats: ${e.localizedMessage}"
            }
        }
    }

    // --- Генерація прогнозів ---
    // Метод для генерації нових прогнозів для станції та оновлення кешу
    fun generateForecasts(stationId: Int, days: Int) {
        viewModelScope.launch {
            try {
                val newForecasts = retryRequest { api.generateForecasts(stationId, days) } // Генеруємо нові прогнози
                val updated = (_forecasts.value[stationId] ?: emptyList()) + newForecasts // Додаємо нові до існуючих
                _forecasts.value = _forecasts.value + (stationId to updated.sortedBy { it.date }) // Оновлюємо список прогнозів
                _forecastsCache[stationId] = updated // Кешуємо
                // Після генерації оновлюємо статистику
                loadStats(stationId)
            } catch (e: Exception) {
                _error.value = "❌ Error generating forecasts: ${e.localizedMessage}"
            }
        }
    }
}