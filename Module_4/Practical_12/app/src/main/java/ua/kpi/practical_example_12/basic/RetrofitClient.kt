package ua.kpi.practical_example_12.basic

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// === Retrofit-клієнт ===
object RetrofitClient {
    // Базова URL-адреса для всіх запитів до API
    private const val BASE_URL = "http://10.0.2.2:5156/" // 10.0.2.2 для доступу до локального API з емулятора

    // Ліниве створення екземпляра інтерфейсу ForecastApiService
    // Використовується lazy-ініціалізація для оптимізації ресурсів
    val api: ForecastApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL) // Встановлює базову URL-адресу для всіх запитів
            .addConverterFactory(GsonConverterFactory.create()) // Додає конвертер для перетворення JSON у об'єкти Kotlin і навпаки
            .build() // Створює екземпляр Retrofit
            .create(ForecastApiService::class.java) // Створює проксі-об'єкт для інтерфейсу ForecastApiService
    }
}