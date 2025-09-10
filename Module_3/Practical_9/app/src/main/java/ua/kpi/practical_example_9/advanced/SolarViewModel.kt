package ua.kpi.practical_example_9.advanced

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SolarViewModel : ViewModel() {
    private val repository = SolarRepository(LocalSolarDataSource(), RemoteSolarDataSource())

    private val _forecasts = MutableStateFlow<List<SolarForecast>>(emptyList())
    val forecasts: StateFlow<List<SolarForecast>> = _forecasts.asStateFlow()

    private val scope = CoroutineScope(Dispatchers.IO)

    init { loadForecasts() }

    fun loadForecasts(forceRefresh: Boolean = false) {
        scope.launch {
            val result = repository.getAll(forceRefresh)
            result.onSuccess { _forecasts.value = it }
                .onFailure { println("Error loading forecasts: ${it.message}") }
        }
    }

    fun addForecast(item: SolarForecast) {
        scope.launch {
            repository.add(item).onSuccess { loadForecasts() }
        }
    }

    fun updateForecast(item: SolarForecast) {
        scope.launch {
            repository.update(item).onSuccess { loadForecasts() }
        }
    }

    fun deleteForecast(id: Int) {
        scope.launch {
            repository.delete(id).onSuccess { loadForecasts() }
        }
    }

    /** Фільтрація у ViewModel */
    fun filterByDay(day: String) {
        _forecasts.value = repository.filterByDay(day)
    }

    /** Сортування у ViewModel */
    fun sortByPower(descending: Boolean = false) {
        _forecasts.value = repository.sortByPower(descending)
    }
}