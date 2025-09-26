package ua.kpi.practical_example_12.medium

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// === Retrofit-клієнт ===
object RetrofitClient {
    // Константа, що містить базову URL-адресу API сервера
    private const val BASE_URL = "http://10.0.2.2:5156/"

    // Лениве створення екземпляра інтерфейсу ForecastApiService
    // Використовується lazy ініціалізація для економії ресурсів
    val api: ForecastApiService by lazy {
        // Створення Retrofit-об'єкта з налаштуваннями
        Retrofit.Builder()
            .baseUrl(BASE_URL)  // Встановлення базової URL-адреси для всіх запитів
            .addConverterFactory(GsonConverterFactory.create())  // Додавання конвертера для JSON (Gson)
            .build()  // Побудова Retrofit-екземпляра
            .create(ForecastApiService::class.java)  // Створення екземпляра API-сервісу
    }
}