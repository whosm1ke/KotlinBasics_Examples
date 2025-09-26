package ua.kpi.practical_example_11.medium

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Створюємо DataStore для зберігання автентифікаційних даних з ім'ям "auth_prefs"
val Context.authDataStore by preferencesDataStore(name = "auth_prefs")

class AuthDataStore(private val context: Context) {

    // Отримуємо посилання на DataStore для поточного контексту
    private val dataStore = context.authDataStore

    // Визначаємо Flow, який слідкує за змінами токена автентифікації
    // Повертає значення токена або null, якщо його немає
    val tokenFlow: Flow<String?> = dataStore.data
        .map { prefs -> prefs[stringPreferencesKey("token")] }

    // Асинхронна функція для збереження токена в DataStore
    suspend fun saveToken(token: String) {
        dataStore.edit { prefs ->
            // Зберігаємо токен за ключем "token"
            prefs[stringPreferencesKey("token")] = token
        }
    }

    // Асинхронна функція для видалення токена з DataStore
    suspend fun clearToken() {
        dataStore.edit { prefs ->
            // Видаляємо запис з ключем "token"
            prefs.remove(stringPreferencesKey("token"))
        }
    }
}