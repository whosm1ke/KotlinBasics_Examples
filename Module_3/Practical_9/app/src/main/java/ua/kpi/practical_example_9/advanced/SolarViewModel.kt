package ua.kpi.practical_example_9.advanced

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SolarViewModel : ViewModel() {
    // Ініціалізація репозиторію з локальним та віддаленим джерелами даних
    private val repository = SolarRepository(LocalSolarDataSource(), RemoteSolarDataSource())

    // Стейт-флоу для зберігання прогнозів сонячної енергії
    private val _forecasts = MutableStateFlow<List<SolarForecast>>(emptyList())
    // Публічний StateFlow для доступу до даних прогнозів
    val forecasts: StateFlow<List<SolarForecast>> = _forecasts.asStateFlow()

    // Створення корутинного скоупу для виконання асинхронних операцій на IO-диспетчері
    private val scope = CoroutineScope(Dispatchers.IO)

    // Ініціалізація: завантаження прогнозів при створенні ViewModel
    init { loadForecasts() }

    // Метод для завантаження прогнозів з можливістю примусового оновлення
    fun loadForecasts(forceRefresh: Boolean = false) {
        scope.launch {
            // Виклик репозиторію для отримання всіх прогнозів
            val result = repository.getAll(forceRefresh)
            // Обробка успішного результату: оновлення стейт-флоу
            result.onSuccess { _forecasts.value = it }
                // Обробка помилки: виведення повідомлення про помилку
                .onFailure { println("Error loading forecasts: ${it.message}") }
        }
    }

    // Метод для додавання нового прогнозу
    fun addForecast(item: SolarForecast) {
        scope.launch {
            // Виклик репозиторію для додавання нового прогнозу
            repository.add(item).onSuccess { loadForecasts() }
        }
    }

    // Метод для оновлення існуючого прогнозу
    fun updateForecast(item: SolarForecast) {
        scope.launch {
            // Виклик репозиторію для оновлення прогнозу
            repository.update(item).onSuccess { loadForecasts() }
        }
    }

    // Метод для видалення прогнозу за ID
    fun deleteForecast(id: Int) {
        scope.launch {
            // Виклик репозиторію для видалення прогнозу
            repository.delete(id).onSuccess { loadForecasts() }
        }
    }

    /** Фільтрація у ViewModel - фільтрує прогнози за днем */
    fun filterByDay(day: String) {
        _forecasts.value = repository.filterByDay(day)
    }

    /** Сортування у ViewModel - сортує прогнози за потужністю */
    fun sortByPower(descending: Boolean = false) {
        _forecasts.value = repository.sortByPower(descending)
    }
}