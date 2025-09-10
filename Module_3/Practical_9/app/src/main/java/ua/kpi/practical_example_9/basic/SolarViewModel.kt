package ua.kpi.practical_example_9.basic

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// ------------------------------------------------------
// ViewModel — працює з Repository і надає дані UI
// ------------------------------------------------------
class SolarViewModel(private val repository: SolarRepository) : ViewModel() {
    private val _forecasts = MutableStateFlow<List<SolarForecast>>(emptyList())
    val forecasts: StateFlow<List<SolarForecast>> = _forecasts.asStateFlow()

    fun loadForecasts() {
        _forecasts.value = repository.getAll()
    }

    fun addForecast(item: SolarForecast) {
        repository.insert(item)
        loadForecasts()
    }
}