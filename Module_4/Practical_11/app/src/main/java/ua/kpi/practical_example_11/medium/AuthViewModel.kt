package ua.kpi.practical_example_11.medium

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AuthViewModel(private val authDataStore: AuthDataStore, val authApi: AuthApi) : ViewModel() {
    // Змінна для зберігання токена користувача, доступна лише для читання
    var token by mutableStateOf<String?>(null)
        private set

    // Змінна для зберігання ролі користувача, доступна лише для читання
    var role by mutableStateOf<String?>(null)
        private set

    // Змінна для зберігання захищених даних, доступна лише для читання
    var protectedData by mutableStateOf<ProtectedMessage?>(null)
        private set

    // Ініціалізація ViewModel: слухаємо зміни токена з AuthDataStore
    init {
        viewModelScope.launch {
            authDataStore.tokenFlow.collect {
                token = it // Оновлюємо значення токена при його зміні
            }
        }
    }

    // Метод для входу користувача
    fun login(username: String, password: String, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                // Викликаємо API для логіну з вказаними обліковими даними
                val response = authApi.login(LoginRequest(username, password))
                // Зберігаємо токен у AuthDataStore
                authDataStore.saveToken(response.token)
                // Оновлюємо значення токена і ролі в ViewModel
                token = response.token
                role = response.role
            } catch (e: Exception) {
                // В разі помилки викликаємо обробник помилки з повідомленням
                onError(e.message ?: "Login failed")
            }
        }
    }

    // Метод для виходу користувача
    fun logout() {
        viewModelScope.launch {
            // Очищуємо токен з AuthDataStore
            authDataStore.clearToken()
            // Скидаємо всі значення у ViewModel
            token = null
            role = null
            protectedData = null
        }
    }

    // Метод для завантаження захищених даних
    fun loadProtectedData(onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                // Викликаємо API для отримання захищених даних
                protectedData = authApi.getProtectedData()
            } catch (e: Exception) {
                // В разі помилки викликаємо обробник помилки з повідомленням
                onError(e.message ?: "Failed to load protected data")
            }
        }
    }
}

class AuthViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {
    // Створюємо ViewModel для класу AuthViewModel
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            // Створюємо екземпляр AuthDataStore з контекстом
            val authDataStore = AuthDataStore(context)

            // Налаштовуємо OkHttpClient з інтерцептором для додавання токена до запитів
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(AuthInterceptor(authDataStore)) // Додаємо інтерцептор для авторизації
                .build()

            // Налаштовуємо Retrofit з базовою URL, клієнтом та конвертером
            val retrofit = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5156/") // Базова URL для API
                .client(okHttpClient) // Використовуємо OkHttpClient з інтерцептором
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            // Створюємо екземпляр AuthApi з Retrofit
            val authApi = retrofit.create(AuthApi::class.java)
            @Suppress("UNCHECKED_CAST")
            // Повертаємо створений AuthViewModel
            return AuthViewModel(authDataStore, authApi) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class") // Якщо клас не підтримується
    }
}