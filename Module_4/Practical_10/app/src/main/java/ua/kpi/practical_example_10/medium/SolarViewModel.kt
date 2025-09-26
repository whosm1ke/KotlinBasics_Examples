package ua.kpi.practical_example_10.medium

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ua.kpi.practical_example_10.basic.Station

class SolarViewModel : ViewModel() {

    // Ініціалізація API клієнта для взаємодії з сервером
    private val api = RetrofitClient.api

    // Стейт-флоу для зберігання списку станцій
    private val _stations = MutableStateFlow<List<Station>>(emptyList())
    // Публічний стейт-флоу для доступу до станцій (тільки для читання)
    val stations: StateFlow<List<Station>> = _stations

    // Стейт-флоу для зберігання прогнозів за ID станції
    private val _forecasts = MutableStateFlow<Map<Int, List<Forecast>>>(emptyMap())
    // Публічний стейт-флоу для доступу до прогнозів (тільки для читання)
    val forecasts: StateFlow<Map<Int, List<Forecast>>> = _forecasts

    // Стейт-флоу для зберігання помилок
    private val _error = MutableStateFlow<String?>(null)
    // Публічний стейт-флоу для доступу до помилок (тільки для читання)
    val error: StateFlow<String?> = _error

    // Стейт-флоу для зберігання стану завантаження
    private val _loading = MutableStateFlow(false)
    // Публічний стейт-флоу для доступу до стану завантаження (тільки для читання)
    val loading: StateFlow<Boolean> = _loading

    // --- Завантаження списку станцій з сервера ---
    fun loadStations() {
        viewModelScope.launch {
            // Встановлюємо стан завантаження у true
            _loading.value = true
            try {
                // Отримуємо дані про станції через API
                val data = api.getStations()
                // Сортуємо отримані дані за назвою станції і зберігаємо у _stations
                _stations.value = data.sortedBy { it.name }
            } catch (e: Exception) {
                // У разі помилки записуємо повідомлення про помилку
                _error.value = "❌ Error loading stations: ${e.localizedMessage}"
            } finally {
                // Встановлюємо стан завантаження у false незалежно від результату
                _loading.value = false
            }
        }
    }

    // --- Завантаження прогнозів для конкретної станції ---
    fun loadForecasts(stationId: Int) {
        viewModelScope.launch {
            try {
                // Отримуємо прогнози для заданої станції через API
                val data = api.getForecasts(stationId)
                // Додаємо отримані прогнози до мапи прогнозів, сортуємо за датою
                _forecasts.value = _forecasts.value + (stationId to data.sortedBy { it.date })
            } catch (e: Exception) {
                // У разі помилки записуємо повідомлення про помилку
                _error.value = "❌ Error loading forecasts: ${e.localizedMessage}"
            }
        }
    }

    // --- Генерація нових прогнозів для конкретної станції ---
    fun generateForecasts(stationId: Int, days: Int) {
        viewModelScope.launch {
            try {
                // Викликаємо API для генерації нових прогнозів
                val newForecasts = api.generateForecasts(stationId, days)
                // Отримуємо існуючі прогнози для цієї станції або створюємо порожній список
                val updated = (_forecasts.value[stationId] ?: emptyList()) + newForecasts
                // Оновлюємо мапу прогнозів з новими значеннями, сортуємо за датою
                _forecasts.value = _forecasts.value + (stationId to updated.sortedBy { it.date })
            } catch (e: Exception) {
                // У разі помилки записуємо повідомлення про помилку
                _error.value = "❌ Error generating forecasts: ${e.localizedMessage}"
            }
        }
    }
}