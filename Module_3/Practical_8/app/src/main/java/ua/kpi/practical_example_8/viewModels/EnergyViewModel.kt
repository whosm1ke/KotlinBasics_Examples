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

    // Створення StateFlow для зберігання списку потужностей
    private val _powers = MutableStateFlow<List<Power>>(emptyList())
    // Публічна властивість для доступу до потужностей з використанням StateFlow
    val powers: StateFlow<List<Power>> = _powers.asStateFlow()

    // Створення StateFlow для зберігання списку напруг
    private val _voltages = MutableStateFlow<List<Voltage>>(emptyList())
    // Публічна властивість для доступу до напруг з використанням StateFlow
    val voltages: StateFlow<List<Voltage>> = _voltages.asStateFlow()

    // Створення StateFlow для зберігання списку температур
    private val _temperatures = MutableStateFlow<List<Temperature>>(emptyList())
    // Публічна властивість для доступу до температур з використанням StateFlow
    val temperatures: StateFlow<List<Temperature>> = _temperatures.asStateFlow()

    // Функція для додавання нової потужності у репозиторій і оновлення списку у ViewModel
    fun addPower(value: Double) {
        viewModelScope.launch {
            repository.insertPower(Power(value = value))  // Вставка нової потужності в репозиторій
            _powers.value = repository.getAllPower()       // Оновлення списку потужностей у ViewModel
        }
    }

    // Функція для додавання нової напруги у репозиторій і оновлення списку у ViewModel
    fun addVoltage(value: Double) {
        viewModelScope.launch {
            repository.insertVoltage(Voltage(value = value))  // Вставка нової напруги в репозиторій
            _voltages.value = repository.getAllVoltage()       // Оновлення списку напруг у ViewModel
        }
    }

    // Функція для додавання нової температури у репозиторій і оновлення списку у ViewModel
    fun addTemperature(value: Double) {
        viewModelScope.launch {
            repository.insertTemperature(Temperature(value = value))  // Вставка нової температури в репозиторій
            _temperatures.value = repository.getAllTemperature()       // Оновлення списку температур у ViewModel
        }
    }

    // Функція для завантаження всіх даних (потужностей, напруг і температур) з репозиторію
    fun loadAll() {
        viewModelScope.launch {
            _powers.value = repository.getAllPower()         // Оновлення списку потужностей
            _voltages.value = repository.getAllVoltage()     // Оновлення списку напруг
            _temperatures.value = repository.getAllTemperature()  // Оновлення списку температур
        }
    }
}