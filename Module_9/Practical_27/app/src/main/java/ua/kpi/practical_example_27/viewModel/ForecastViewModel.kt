package ua.kpi.practical_example_27.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ua.kpi.practical_example_27.models.SolarForecast
import ua.kpi.practical_example_27.models.User
import ua.kpi.practical_example_27.repository.ForecastRepository

// ------------------- ViewModel -------------------
// Клас ForecastViewModel відповідає за управління даними прогнозу сонячної енергії
// Він наслідує ViewModel, що забезпечує коректну роботу з життєвим циклом компонента
class ForecastViewModel : ViewModel() {

    // Ініціалізація репозиторію для отримання даних прогнозу
    private val repository = ForecastRepository()

    // Створення StateFlow для зберігання сьогоднішнього прогнозу
    // MutableStateFlow дозволяє змінювати значення, а StateFlow — тільки читати
    private val _todayForecast = MutableStateFlow<List<SolarForecast>>(emptyList())
    val todayForecast: StateFlow<List<SolarForecast>> = _todayForecast

    // Створення StateFlow для зберігання історичного прогнозу
    private val _historicalForecast = MutableStateFlow<List<SolarForecast>>(emptyList())
    val historicalForecast: StateFlow<List<SolarForecast>> = _historicalForecast

    // Створення StateFlow для відстеження стану авторизації користувача
    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

    // Метод для входу користувача
    // Приймає об'єкт User з ім'ям користувача та паролем
    fun login(user: User) {
        viewModelScope.launch {
            delay(500) // Симуляція мережевого запиту (затримка 500 мс)
            if (user.username.isNotEmpty() && user.password.isNotEmpty()) {
                // Якщо обидва поля не порожні — вважаємо користувача авторизованим
                _isLoggedIn.value = true
                loadForecasts() // Завантаження прогнозів після авторизації
            } else {
                // Якщо хоча б одне поле порожнє — встановлюємо стан неавторизованого користувача
                _isLoggedIn.value = false
            }
        }
    }

    // Приватний метод для завантаження прогнозів
    // Викликає методи репозиторію для отримання сьогоднішнього та історичного прогнозів
    private fun loadForecasts() {
        _todayForecast.value = repository.getTodayForecast()
        _historicalForecast.value = repository.getHistoricalForecast()
    }

    // Метод для експорту даних у форматі CSV
    // Приймає callback-функцію onComplete, яка викликається після завершення експорту
    fun exportCSV(onComplete: (String) -> Unit) {
        viewModelScope.launch {
            delay(500) // Симуляція процесу експорту (затримка 500 мс)
            onComplete("Файл CSV успішно згенеровано!") // Повідомлення про успішне завершення
        }
    }

    // Метод для відправки сповіщення
    // Приймає callback-функцію onNotify, яка викликається з повідомленням про оновлення прогнозу
    fun sendNotification(onNotify: (String) -> Unit) {
        onNotify("Прогноз потужності оновлено!") // Відправляємо сповіщення
    }
}