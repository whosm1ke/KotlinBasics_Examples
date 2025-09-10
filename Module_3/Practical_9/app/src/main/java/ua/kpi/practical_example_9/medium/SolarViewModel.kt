package ua.kpi.practical_example_9.medium

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// ------------------------------------------------------
// ViewModel
// ------------------------------------------------------
class SolarViewModel : ViewModel() {
    private val repository = SolarRepository(LocalSolarDataSource(), RemoteSolarDataSource())

    private val _forecasts = MutableStateFlow<List<SolarForecast>>(emptyList())
    val forecasts: StateFlow<List<SolarForecast>> = _forecasts.asStateFlow()

    private val scope = CoroutineScope(Dispatchers.IO)

    init {
        loadForecasts()
    }

    fun loadForecasts() {
        scope.launch {
            val list = repository.getAll()
            _forecasts.value = list
        }
    }

    fun addForecast(item: SolarForecast) {
        scope.launch {
            repository.add(item)
            _forecasts.value = repository.getAll()
        }
    }

    fun updateForecast(item: SolarForecast) {
        scope.launch {
            repository.update(item)
            _forecasts.value = repository.getAll()
        }
    }

    fun deleteForecast(id: Int) {
        scope.launch {
            repository.delete(id)
            _forecasts.value = repository.getAll()
        }
    }

    fun findForecast(id: Int): SolarForecast? = repository.findById(id)
}