package ua.kpi.practical_example_7.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AdvancedViewModel : ViewModel() {
    // Поточна потужність - внутрішній StateFlow для зберігання значення потужності
    private val _power = MutableStateFlow(0)
    // Публічний StateFlow для спостереження ззовні
    val power: StateFlow<Int> = _power.asStateFlow()

    // Статус - внутрішній StateFlow для зберігання статусу системи
    private val _status = MutableStateFlow("Норма")
    // Публічний StateFlow для спостереження ззовні
    val status: StateFlow<String> = _status.asStateFlow()

    // Стан завантаження даних - внутрішній StateFlow для відстеження процесу завантаження
    private val _isLoading = MutableStateFlow(false)
    // Публічний StateFlow для спостереження ззовні
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    // Функція для асинхронного оновлення потужності
    fun refreshPower() {
        // Запускаємо корутину в межах viewModelScope (він автоматично скасується при знищенні ViewModel)
        viewModelScope.launch {
            // Встановлюємо стан завантаження у true
            _isLoading.value = true
            
            // Імітуємо затримку завантаження даних (1 секунда)
            delay(1000)
            
            // Генеруємо випадкове значення потужності в діапазоні 0-100
            val newPower = (0..100).random()
            
            // Оновлюємо значення потужності
            _power.value = newPower
            
            // Визначаємо статус на основі нового значення потужності
            _status.value = when {
                newPower < 30 -> "Низька потужність"  // Якщо менше 30 - низька потужність
                newPower > 70 -> "Висока потужність"  // Якщо більше 70 - висока потужність
                else -> "Норма"                       // В інших випадках - норма
            }
            
            // Після завершення оновлення встановлюємо стан завантаження у false
            _isLoading.value = false
        }
    }
}