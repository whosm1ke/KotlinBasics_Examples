package ua.kpi.practical_example_11.basic

import retrofit2.http.Body
import retrofit2.http.POST

// --- Retrofit API для базового рівня ---
// Цей інтерфейс визначає контракт для взаємодії з API аутентифікації
interface AuthApi {
    // Метод для виконання логіну користувача
    // Використовує HTTP POST запит на шлях "login"
    // @Body вказує, що об'єкт LoginRequest буде переданий у тілі запиту
    // Повертає об'єкт типу LoginResponse після успішного виконання
    @POST("login")
    suspend fun login(@Body request: LoginRequest): LoginResponse
}