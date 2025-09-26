package ua.kpi.practical_example_12.advanced

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// === Основний клієнт Retrofit для взаємодії з API ===
object RetrofitClient {
    // Базова URL-адреса сервера, до якого будуть надсилатися запити
    private const val BASE_URL = "http://10.0.2.2:5156/"

    // Лениве ініціалізація сервісу API для отримання даних про погоду
    // Використовується lazy-ініціалізація, щоб створити об'єкт лише при першому виклику
    val api: ForecastApiService by lazy {
        // Створюємо екземпляр Retrofit з налаштуваннями
        Retrofit.Builder()
            .baseUrl(BASE_URL)  // Встановлюємо базову URL-адресу для всіх запитів
            .addConverterFactory(GsonConverterFactory.create())  // Додаємо конвертер JSON у об'єкти Kotlin за допомогою Gson
            .build()  // Створюємо екземпляр Retrofit
            .create(ForecastApiService::class.java)  // Створюємо інстанс інтерфейсу ForecastApiService для виконання запитів
    }
}