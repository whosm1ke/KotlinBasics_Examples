package ua.kpi.practical_example_10.basic

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// ----------------------------
// Опис класу RetrofitClient
// ----------------------------
object RetrofitClient {
    // Константа, що містить базову URL-адресу API для зв'язку з сервером
    private const val BASE_URL = "http://10.0.2.2:5156/" // localhost для емулятора

    // Властивість api типу SolarApiService, яка ініціалізується ліниво (lazy initialization)
    // Це гарантує, що Retrofit буде створено лише при першому використанні
    val api: SolarApiService by lazy {
        // Створюємо екземпляр Retrofit з налаштуваннями:
        // 1. Вказуємо базову URL-адресу для всіх запитів
        // 2. Додаємо конвертер Gson для автоматичного парсингу JSON в об'єкти Kotlin
        // 3. Створюємо екземпляр Retrofit із заданими параметрами
        // 4. Використовуємо метод create() для створення інстансу інтерфейсу SolarApiService
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SolarApiService::class.java)
    }
}