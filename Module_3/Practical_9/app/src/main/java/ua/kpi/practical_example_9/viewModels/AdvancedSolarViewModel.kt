package ua.kpi.practical_example_9.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ua.kpi.practical_example_9.api.AdvancedFakeApiService
import ua.kpi.practical_example_9.data.SolarPower
import ua.kpi.practical_example_9.repositories.AdvancedSolarRepository
import ua.kpi.practical_example_9.sources.AdvancedLocalSolarDataSource

class AdvancedSolarViewModel(private val repository: AdvancedSolarRepository) : ViewModel() {
    private val _powerList = MutableStateFlow<List<SolarPower>>(emptyList())
    val powerList: StateFlow<List<SolarPower>> = _powerList.asStateFlow()

    private val _averagePower = MutableStateFlow(0.0)
    val averagePower: StateFlow<Double> = _averagePower.asStateFlow()

    private val viewModelScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    init { loadPowerData() }

    fun loadPowerData(forceRefresh: Boolean = false) {
        viewModelScope.launch {
            _powerList.value = repository.getAllPower(forceRefresh)
            _averagePower.value = repository.calculateAveragePower()
        }
    }

    fun addPower(day: String, power: Double) {
        if (day.isBlank() || power < 0) return
        viewModelScope.launch {
            repository.addPower(SolarPower(day, power))
            loadPowerData()
        }
    }

    fun deletePower(day: String) {
        viewModelScope.launch {
            repository.deletePower(day)
            loadPowerData()
        }
    }

    fun updatePower(day: String, power: Double) {
        viewModelScope.launch {
            repository.updatePower(SolarPower(day, power))
            loadPowerData()
        }

    }

    fun filterAndSort(query: String, ascending: Boolean = true) {
        viewModelScope.launch {
            _powerList.value = repository.filterAndSort(query, ascending)
        }
    }
}

class AdvancedSolarViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val local = AdvancedLocalSolarDataSource()
        val repo = AdvancedSolarRepository(local)
        return AdvancedSolarViewModel(repo) as T
    }
}