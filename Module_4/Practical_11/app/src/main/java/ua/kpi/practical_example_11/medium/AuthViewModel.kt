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
    var token by mutableStateOf<String?>(null)
        private set

    var role by mutableStateOf<String?>(null)
        private set

    var protectedData by mutableStateOf<ProtectedMessage?>(null)
        private set

    init {
        viewModelScope.launch {
            authDataStore.tokenFlow.collect {
                token = it
            }
        }
    }

    fun login(username: String, password: String, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val response = authApi.login(LoginRequest(username, password))
                authDataStore.saveToken(response.token)
                token = response.token
                role = response.role
            } catch (e: Exception) {
                onError(e.message ?: "Login failed")
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            authDataStore.clearToken()
            token = null
            role = null
            protectedData = null
        }
    }

    fun loadProtectedData(onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                protectedData = authApi.getProtectedData()
            } catch (e: Exception) {
                onError(e.message ?: "Failed to load protected data")
            }
        }
    }
}

class AuthViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            val authDataStore = AuthDataStore(context)

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(AuthInterceptor(authDataStore)) // додаємо інтерцептор
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5156/")
                .client(okHttpClient) // передаємо OkHttpClient з інтерсептором
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val authApi = retrofit.create(AuthApi::class.java)
            @Suppress("UNCHECKED_CAST")
            return AuthViewModel(authDataStore, authApi) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}