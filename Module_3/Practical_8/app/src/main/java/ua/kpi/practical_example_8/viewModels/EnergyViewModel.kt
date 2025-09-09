package ua.kpi.practical_example_8.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ua.kpi.practical_example_8.data.Power
import ua.kpi.practical_example_8.data.Temperature
import ua.kpi.practical_example_8.data.Voltage
import ua.kpi.practical_example_8.repositories.EnergyRepository

class EnergyViewModel(private val repository: EnergyRepository) : ViewModel() {

    private val _powers = MutableStateFlow<List<Power>>(emptyList())
    val powers: StateFlow<List<Power>> = _powers.asStateFlow()

    private val _voltages = MutableStateFlow<List<Voltage>>(emptyList())
    val voltages: StateFlow<List<Voltage>> = _voltages.asStateFlow()

    private val _temperatures = MutableStateFlow<List<Temperature>>(emptyList())
    val temperatures: StateFlow<List<Temperature>> = _temperatures.asStateFlow()

    fun addPower(value: Double) {
        viewModelScope.launch {
            repository.insertPower(Power(value = value))
            _powers.value = repository.getAllPower()
        }
    }

    fun addVoltage(value: Double) {
        viewModelScope.launch {
            repository.insertVoltage(Voltage(value = value))
            _voltages.value = repository.getAllVoltage()
        }
    }

    fun addTemperature(value: Double) {
        viewModelScope.launch {
            repository.insertTemperature(Temperature(value = value))
            _temperatures.value = repository.getAllTemperature()
        }
    }

    fun loadAll() {
        viewModelScope.launch {
            _powers.value = repository.getAllPower()
            _voltages.value = repository.getAllVoltage()
            _temperatures.value = repository.getAllTemperature()
        }
    }
}