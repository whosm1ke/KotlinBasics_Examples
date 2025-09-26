package ua.kpi.practical_example_9.basic

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// ------------------------------------------------------
// ViewModel — працює з Repository і надає дані UI
// Він відповідає за управління даними та їх оновлення для подальшого використання в UI
// ------------------------------------------------------
class SolarViewModel(private val repository: SolarRepository) : ViewModel() {
    // Створюємо MutableStateFlow для зберігання прогнозів сонячної енергії
    // Він є змінним, тому може бути змінений
    private val _forecasts = MutableStateFlow<List<SolarForecast>>(emptyList())
    
    // Створюємо StateFlow для надання прогнозів зовнішньому світу
    // Він є незмінним (read-only), тому не може бути змінений ззовні
    val forecasts: StateFlow<List<SolarForecast>> = _forecasts.asStateFlow()

    // Функція для завантаження всіх прогнозів з репозиторію
    // Оновлює значення _forecasts, отримуючи дані від repository
    fun loadForecasts() {
        _forecasts.value = repository.getAll()
    }

    // Функція для додавання нового прогнозу
    // Вставляє новий запис у репозиторій і оновлює список прогнозів
    fun addForecast(item: SolarForecast) {
        repository.insert(item)
        loadForecasts()
    }
}