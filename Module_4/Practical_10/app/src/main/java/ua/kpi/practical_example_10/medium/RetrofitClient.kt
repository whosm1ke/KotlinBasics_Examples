package ua.kpi.practical_example_10.medium

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// ----------------------------
// Retrofit instance
// ----------------------------
object RetrofitClient {
    // Константа, що містить базову URL-адресу для API
    private const val BASE_URL = "http://10.0.2.2:5156/" // localhost для емулятора

    // Ленива ініціалізація екземпляра Retrofit для отримання доступу до API
    val api: SolarApiService by lazy {
        // Створення екземпляра Retrofit з налаштуваннями
        Retrofit.Builder()
            .baseUrl(BASE_URL) // Встановлення базової URL-адреси
            .addConverterFactory(GsonConverterFactory.create()) // Додавання конвертера для JSON (Gson)
            .build() // Побудова екземпляра Retrofit
            .create(SolarApiService::class.java) // Створення інстансу API-сервісу
    }
}