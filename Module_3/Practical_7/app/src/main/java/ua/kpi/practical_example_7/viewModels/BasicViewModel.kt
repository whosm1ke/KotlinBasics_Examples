package ua.kpi.practical_example_7.viewModels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class BasicViewModel : ViewModel() {
    // Внутрішній MutableStateFlow для зберігання поточного значення потужності сонячної станції
    private val _power = MutableStateFlow(0)
    
    // Публічний StateFlow для спостереження за значенням потужності (тільки для читання)
    val power: StateFlow<Int> = _power.asStateFlow()

    // Функція для оновлення значення потужності
    // Приймає нове значення потужності типу Int і оновлює внутрішнє стану
    fun updatePower(newPower: Int) {
        _power.value = newPower
    }
}