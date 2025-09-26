package ua.kpi.practical_example_11.basic

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

// --- ViewModel для збереження токена ---
// Цей клас є ViewModel, який використовується для зберігання та управління станом токена користувача
// Він успадковує від ViewModel, що дозволяє йому зберігати дані між перезавантаженнями активностей
class AuthViewModel : ViewModel() {
    // Токен користувача (mutableState для реактивного оновлення UI)
    // Використовується mutableStateOf для створення реактивного стану, який автоматично оновлює компоненти Compose при зміні значення
    var token by mutableStateOf<String?>(null)
        private set

    // Роль користувача (mutableState для реактивного оновлення UI)
    // Зберігає роль користувача, яка також може бути реактивно відображена в інтерфейсі
    var role by mutableStateOf<String?>(null)
        private set

    // Метод для збереження нового токена та ролі користувача
    // Приймає новий токен та роль, які будуть встановлені у відповідні властивості
    fun saveToken(newToken: String, newRole: String) {
        token = newToken
        role = newRole
    }

    // Метод для очищення токена та ролі користувача
    // Встановлює значення токена та ролі на null, що вказує на неавторизований стан користувача
    fun clearToken() {
        token = null
        role = null
    }
}