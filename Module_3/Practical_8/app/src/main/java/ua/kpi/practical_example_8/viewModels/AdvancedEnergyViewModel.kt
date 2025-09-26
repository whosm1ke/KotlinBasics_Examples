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
    // MutableStateFlow для зберігання списку сонячних станцій
    private val _stations = MutableStateFlow<List<SolarStation>>(emptyList())
    // Публічний StateFlow для доступу до списку станцій (тільки для читання)
    val stations: StateFlow<List<SolarStation>> = _stations.asStateFlow()

    // MutableStateFlow для зберігання ID обраної станції
    private val _selectedStationId = MutableStateFlow<Int?>(null)
    // Публічний StateFlow для доступу до ID обраної станції (тільки для читання)
    val selectedStationId: StateFlow<Int?> = _selectedStationId.asStateFlow()

    // MutableStateFlow для зберігання списку потужностей
    private val _powers = MutableStateFlow<List<SolarPower>>(emptyList())
    // Публічний StateFlow для доступу до списку потужностей (тільки для читання)
    val powers: StateFlow<List<SolarPower>> = _powers.asStateFlow()

    // MutableStateFlow для зберігання списку напруг
    private val _voltages = MutableStateFlow<List<SolarVoltage>>(emptyList())
    // Публічний StateFlow для доступу до списку напруг (тільки для читання)
    val voltages: StateFlow<List<SolarVoltage>> = _voltages.asStateFlow()

    /**
     * Вибирає станцію за ID і завантажує дані про потужність та напругу для неї.
     * @param id - ID обраної станції
     */
    fun selectStation(id: Int) {
        // Оновлює ID обраної станції
        _selectedStationId.value = id
        // Запускає корутину в рамках ViewModelScope для асинхронного завантаження даних
        viewModelScope.launch {
            // Оновлює список потужностей для обраної станції
            _powers.value = repository.getPowersForStation(id)
            // Оновлює список напруг для обраної станції
            _voltages.value = repository.getVoltagesForStation(id)
        }
    }

    /**
     * Завантажує всі сонячні станції з репозиторію.
     */
    fun loadStations() {
        // Запускає корутину в рамках ViewModelScope для асинхронного завантаження даних
        viewModelScope.launch {
            // Оновлює список станцій
            _stations.value = repository.getAllStations()
        }
    }

    /**
     * Додає нову станцію разом із даними про потужність та напругу.
     * @param name - Назва нової станції
     * @param powersList - Список значень потужності
     * @param voltagesList - Список значень напруги
     */
    fun addStationWithData(name: String, powersList: List<Double>, voltagesList: List<Double>) {
        // Запускає корутину в рамках ViewModelScope для асинхронного додавання даних
        viewModelScope.launch {
            // Створює нову станцію з заданою назвою
            val station = SolarStation(name = name)
            // Створює список потужностей на основі переданого списку значень
            val powers = powersList.map { SolarPower(value = it, stationId = 0) }
            // Створює список напруг на основі переданого списку значень
            val voltages = voltagesList.map { SolarVoltage(value = it, stationId = 0) }
            // Вставляє нову станцію разом із даними в репозиторій
            repository.insertStationWithData(station, powers, voltages)
            // Повторно завантажує список станцій після додавання нової
            loadStations()
        }
    }
}