package ua.kpi.practical_example_9.basic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

// Фабрика для створення ViewModel з репозиторієм
// Використовується для ініціалізації SolarViewModel з переданим репозиторієм
class SolarViewModelFactory(private val repository: SolarRepository) :
    ViewModelProvider.Factory {
    
    // Метод, що відповідає за створення екземпляра ViewModel
    // Приймає клас ViewModel, який потрібно створити
    // Повертає екземпляр SolarViewModel, ініціалізований переданим репозиторієм
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SolarViewModel(repository) as T
    }
}