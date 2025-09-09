package ua.kpi.practical_example_7.viewModels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MediumViewModel : ViewModel() {
    private val _currentPower = MutableStateFlow(0)
    val currentPower: StateFlow<Int> = _currentPower.asStateFlow()

    private val _status = MutableStateFlow("Норма")
    val status: StateFlow<String> = _status.asStateFlow()

    // Оновлення потужності та розрахунок статусу
    fun updatePower(newPower: Int) {
        _currentPower.value = newPower
        _status.value = when {
            newPower < 30 -> "Низька потужність"
            newPower > 70 -> "Висока потужність"
            else -> "Норма"
        }
    }
}
