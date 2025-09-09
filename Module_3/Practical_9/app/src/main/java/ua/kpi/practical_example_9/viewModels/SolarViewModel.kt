package ua.kpi.practical_example_9.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ua.kpi.practical_example_9.data.SolarPower
import ua.kpi.practical_example_9.repositories.SolarRepository
import ua.kpi.practical_example_9.sources.LocalSolarDataSource

// --- ViewModel Layer ---
class SolarViewModel(private val repository: SolarRepository) : ViewModel() {

    // Використовуємо StateFlow для реактивного UI
    private val _powerList = MutableStateFlow<List<SolarPower>>(emptyList())
    val powerList: StateFlow<List<SolarPower>> get() = _powerList

    init {
        // Ініціалізація даних із Repository
        loadPowerData()
    }

    private fun loadPowerData() {
        _powerList.value = repository.getAllPower()
    }

    // Метод для додавання нового прогнозу
    fun addPower(day: String, power: Double) {
        if (day.isNotBlank() && power >= 0.0) { // базова валідація
            repository.addPower(SolarPower(day, power))
            loadPowerData() // оновлюємо StateFlow
        }
    }
}

class SolarViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val localDataSource = LocalSolarDataSource()
        val repository = SolarRepository(localDataSource)
        return SolarViewModel(repository) as T
    }
}

