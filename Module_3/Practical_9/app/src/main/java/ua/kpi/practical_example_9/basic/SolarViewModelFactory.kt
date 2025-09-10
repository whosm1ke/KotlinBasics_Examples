package ua.kpi.practical_example_9.basic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

// Фабрика для створення ViewModel з репозиторієм
class SolarViewModelFactory(private val repository: SolarRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SolarViewModel(repository) as T
    }
}