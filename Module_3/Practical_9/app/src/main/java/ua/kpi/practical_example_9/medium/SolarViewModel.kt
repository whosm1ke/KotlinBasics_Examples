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
    // Ініціалізація репозиторію з локальним та віддаленим джерелом даних
    private val repository = SolarRepository(LocalSolarDataSource(), RemoteSolarDataSource())

    // Створення MutableStateFlow для зберігання прогнозів сонячної енергії
    private val _forecasts = MutableStateFlow<List<SolarForecast>>(emptyList())
    
    // Публічний StateFlow для спостереження за прогнозами
    val forecasts: StateFlow<List<SolarForecast>> = _forecasts.asStateFlow()

    // Створення корутинної області дій з диспетчером IO
    private val scope = CoroutineScope(Dispatchers.IO)

    // Ініціалізація: завантаження прогнозів при створенні ViewModel
    init {
        loadForecasts()
    }

    // Функція для завантаження всіх прогнозів з репозиторію
    fun loadForecasts() {
        scope.launch {
            val list = repository.getAll()
            _forecasts.value = list
        }
    }

    // Додавання нового прогнозу
    fun addForecast(item: SolarForecast) {
        scope.launch {
            repository.add(item)
            _forecasts.value = repository.getAll() // Оновлення стану після додавання
        }
    }

    // Оновлення існуючого прогнозу
    fun updateForecast(item: SolarForecast) {
        scope.launch {
            repository.update(item)
            _forecasts.value = repository.getAll() // Оновлення стану після оновлення
        }
    }

    // Видалення прогнозу за ідентифікатором
    fun deleteForecast(id: Int) {
        scope.launch {
            repository.delete(id)
            _forecasts.value = repository.getAll() // Оновлення стану після видалення
        }
    }

    // Пошук прогнозу за ідентифікатором
    fun findForecast(id: Int): SolarForecast? = repository.findById(id)
}