package ua.kpi.practical_example_11.medium

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.authDataStore by preferencesDataStore(name = "auth_prefs")
class AuthDataStore(private val context: Context) {

    private val dataStore = context.authDataStore

    val tokenFlow: Flow<String?> = dataStore.data
        .map { prefs -> prefs[stringPreferencesKey("token")] }

    suspend fun saveToken(token: String) {
        dataStore.edit { prefs ->
            prefs[stringPreferencesKey("token")] = token
        }
    }

    suspend fun clearToken() {
        dataStore.edit { prefs ->
            prefs.remove(stringPreferencesKey("token"))
        }
    }
}
