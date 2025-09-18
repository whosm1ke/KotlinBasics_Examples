package ua.kpi.practical_example_27.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ua.kpi.practical_example_27.models.SolarForecast
import ua.kpi.practical_example_27.models.User
import ua.kpi.practical_example_27.repository.ForecastRepository

// ------------------- ViewModel -------------------
class ForecastViewModel : ViewModel() {

    private val repository = ForecastRepository()

    private val _todayForecast = MutableStateFlow<List<SolarForecast>>(emptyList())
    val todayForecast: StateFlow<List<SolarForecast>> = _todayForecast

    private val _historicalForecast = MutableStateFlow<List<SolarForecast>>(emptyList())
    val historicalForecast: StateFlow<List<SolarForecast>> = _historicalForecast

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

    // Авторизація користувача
    fun login(user: User) {
        viewModelScope.launch {
            delay(500) // симуляція мережі
            if (user.username.isNotEmpty() && user.password.isNotEmpty()) {
                _isLoggedIn.value = true
                loadForecasts()
            } else {
                _isLoggedIn.value = false
            }
        }
    }

    // Завантаження прогнозів
    private fun loadForecasts() {
        _todayForecast.value = repository.getTodayForecast()
        _historicalForecast.value = repository.getHistoricalForecast()
    }

    // Симуляція експорту даних CSV
    fun exportCSV(onComplete: (String) -> Unit) {
        viewModelScope.launch {
            delay(500) // симуляція процесу
            onComplete("Файл CSV успішно згенеровано!")
        }
    }

    // Симуляція локального сповіщення
    fun sendNotification(onNotify: (String) -> Unit) {
        onNotify("Прогноз потужності оновлено!")
    }
}