package ua.kpi.practical_example_7.viewModels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class BasicViewModel : ViewModel() {
    // StateFlow для поточного показника потужності сонячної станції
    private val _power = MutableStateFlow(0)
    val power: StateFlow<Int> = _power.asStateFlow()

    // Функція для оновлення потужності
    fun updatePower(newPower: Int) {
        _power.value = newPower
    }
}