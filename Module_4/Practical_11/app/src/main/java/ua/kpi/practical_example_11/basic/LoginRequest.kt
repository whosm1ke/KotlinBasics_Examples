package ua.kpi.practical_example_11.basic

import com.google.gson.annotations.SerializedName

// --- Дані запиту та відповіді ---
// Клас, що представляє запит на логін користувача
// Використовується для серіалізації/десеріалізації даних у форматі JSON
// Застосовує анотації @SerializedName для вказівки назв полів у JSON-об'єкті

data class LoginRequest(
    @SerializedName("Username") val username: String,  // Поле для імені користувача, серіалізується як "Username" у JSON
    @SerializedName("Password")val password: String)   // Поле для пароля, серіалізується як "Password" у JSON
