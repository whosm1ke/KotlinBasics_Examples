package ua.kpi.practical_example_11.advanced

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
import ua.kpi.practical_example_11.medium.AuthDataStore
import ua.kpi.practical_example_11.medium.AuthInterceptor
import ua.kpi.practical_example_11.medium.LoginRequest
import ua.kpi.practical_example_11.medium.SolarStation

class AuthViewModel(
    private val authDataStore: AuthDataStore,
    private val authApi: AuthApi
) : ViewModel() {

    // Стан для зберігання токена користувача
    var token by mutableStateOf<String?>(null)
        private set

    // Стан для зберігання ролі користувача
    var role by mutableStateOf<String?>(null)
        private set

    // Стан для захищених даних
    var protectedData by mutableStateOf<ProtectedMessage?>(null)
        private set

    // Стан для даних, доступних лише адмінам
    var adminData by mutableStateOf<List<SolarStation>?>(null)
        private set

    init {
        // Слухаємо зміни токена у DataStore і оновлюємо стан
        viewModelScope.launch {
            authDataStore.tokenFlow.collect {
                token = it
            }
        }
    }

    // Метод для входу користувача
    fun login(username: String, password: String, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                // Викликаємо API для логіну
                val response = authApi.login(LoginRequest(username, password))
                // Зберігаємо токен у DataStore
                authDataStore.saveToken(response.token)
                // Оновлюємо стан токена і ролі
                token = response.token
                role = response.role
            } catch (e: Exception) {
                // Викликаємо обробник помилки при невдачі
                onError(e.message ?: "Login failed")
            }
        }
    }

    // Метод для виходу користувача
    fun logout() {
        viewModelScope.launch {
            // Очищуємо токен у DataStore
            authDataStore.clearToken()
            // Скидаємо всі стани
            token = null
            role = null
            protectedData = null
            adminData = null
        }
    }

    // Метод для завантаження захищених даних
    fun loadProtectedData(onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                // Викликаємо API для отримання захищених даних
                protectedData = authApi.getProtectedData()
            } catch (e: Exception) {
                // Викликаємо обробник помилки при невдачі
                onError(e.message ?: "Failed to load protected data")
            }
        }
    }

    // Метод для завантаження даних, доступних лише адмінам
    fun loadAdminData(onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                // Викликаємо API для отримання даних адміна
                adminData = authApi.getAdminOnly()
            } catch (e: Exception) {
                // Викликаємо обробник помилки при невдачі
                onError(e.message ?: "Failed to load admin data")
            }
        }
    }
}

class AuthViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Перевіряємо, чи клас відповідає AuthViewModel
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            // Створюємо екземпляр AuthDataStore для зберігання даних
            val authDataStore = AuthDataStore(context)

            // Створюємо OkHttpClient з інтерцептором для додавання токена до запитів
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(AuthInterceptor(authDataStore)) // Додаємо інтерцептор для авторизації
                .build()

            // Створюємо Retrofit з базовою URL-адресою та клієнтом
            val retrofit = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5156/")
                .client(okHttpClient) // Передаємо OkHttpClient з інтерсептором
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            // Створюємо API-клієнт для AuthApi
            val authApi = retrofit.create(AuthApi::class.java)
            @Suppress("UNCHECKED_CAST")
            // Повертаємо екземпляр AuthViewModel
            return AuthViewModel(authDataStore, authApi) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}