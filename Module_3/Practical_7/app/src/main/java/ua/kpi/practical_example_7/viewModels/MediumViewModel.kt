package ua.kpi.practical_example_7.viewModels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MediumViewModel : ViewModel() {
    // Оголошення внутрішнього StateFlow для зберігання поточного рівня потужності
    private val _currentPower = MutableStateFlow(0)
    // Публічний StateFlow для доступу до значення поточного рівня потужності
    val currentPower: StateFlow<Int> = _currentPower.asStateFlow()

    // Оголошення внутрішнього StateFlow для зберігання статусу
    private val _status = MutableStateFlow("Норма")
    // Публічний StateFlow для доступу до значення статусу
    val status: StateFlow<String> = _status.asStateFlow()

    // Функція для оновлення потужності та визначення нового статусу на основі нової потужності
    fun updatePower(newPower: Int) {
        // Оновлення значення поточного рівня потужності
        _currentPower.value = newPower
        // Визначення нового статусу залежно від значення нової потужності
        _status.value = when {
            // Якщо потужність менше 30, статус - "Низька потужність"
            newPower < 30 -> "Низька потужність"
            // Якщо потужність більше 70, статус - "Висока потужність"
            newPower > 70 -> "Висока потужність"
            // В іншому випадку статус - "Норма"
            else -> "Норма"
        }
    }
}