package ua.kpi.practical_example_8.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ua.kpi.practical_example_8.data.SolarPower
import ua.kpi.practical_example_8.data.SolarStation
import ua.kpi.practical_example_8.data.SolarVoltage
import ua.kpi.practical_example_8.repositories.AdvancedEnergyRepository
import kotlin.collections.emptyList

class AdvancedEnergyViewModel(private val repository: AdvancedEnergyRepository) : ViewModel() {

    private val _stations = MutableStateFlow<List<SolarStation>>(emptyList())
    val stations: StateFlow<List<SolarStation>> = _stations.asStateFlow()

    private val _selectedStationId = MutableStateFlow<Int?>(null)
    val selectedStationId: StateFlow<Int?> = _selectedStationId.asStateFlow()

    private val _powers = MutableStateFlow<List<SolarPower>>(emptyList())
    val powers: StateFlow<List<SolarPower>> = _powers.asStateFlow()

    private val _voltages = MutableStateFlow<List<SolarVoltage>>(emptyList())
    val voltages: StateFlow<List<SolarVoltage>> = _voltages.asStateFlow()

    fun selectStation(id: Int) {
        _selectedStationId.value = id
        viewModelScope.launch {
            _powers.value = repository.getPowersForStation(id)
            _voltages.value = repository.getVoltagesForStation(id)
        }
    }

    fun loadStations() {
        viewModelScope.launch {
            _stations.value = repository.getAllStations()
        }
    }

    fun addStationWithData(name: String, powersList: List<Double>, voltagesList: List<Double>) {
        viewModelScope.launch {
            val station = SolarStation(name = name)
            val powers = powersList.map { SolarPower(value = it, stationId = 0) }
            val voltages = voltagesList.map { SolarVoltage(value = it, stationId = 0) }
            repository.insertStationWithData(station, powers, voltages)
            loadStations()
        }
    }
}