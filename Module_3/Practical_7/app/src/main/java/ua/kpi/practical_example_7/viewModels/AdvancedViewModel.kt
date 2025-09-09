package ua.kpi.practical_example_7.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AdvancedViewModel : ViewModel() {
    // Поточна потужність
    private val _power = MutableStateFlow(0)
    val power: StateFlow<Int> = _power.asStateFlow()

    // Статус
    private val _status = MutableStateFlow("Норма")
    val status: StateFlow<String> = _status.asStateFlow()

    // Стан завантаження даних
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    // Функція для асинхронного оновлення потужності
    fun refreshPower() {
        // Запускаємо корутину
        viewModelScope.launch {
            _isLoading.value = true
            delay(1000) // імітація завантаження даних
            val newPower = (0..100).random()
            _power.value = newPower
            _status.value = when {
                newPower < 30 -> "Низька потужність"
                newPower > 70 -> "Висока потужність"
                else -> "Норма"
            }
            _isLoading.value = false
        }
    }
}