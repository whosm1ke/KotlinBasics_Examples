package ua.kpi.practical_example_11.basic

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

// --- ViewModel для збереження токена ---
class AuthViewModel : ViewModel() {
    // Токен користувача (mutableState для реактивного оновлення UI)
    var token by mutableStateOf<String?>(null)
        private set

    var role by mutableStateOf<String?>(null)
        private set

    fun saveToken(newToken: String, newRole: String) {
        token = newToken
        role = newRole
    }

    fun clearToken() {
        token = null
        role = null
    }
}