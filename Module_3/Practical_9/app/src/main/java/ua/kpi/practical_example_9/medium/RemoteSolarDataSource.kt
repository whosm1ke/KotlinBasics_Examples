package ua.kpi.practical_example_9.medium

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
// ------------------------------------------------------
// Remote Data Source через Retrofit
// Цей клас відповідає за взаємодію з віддаленим API для роботи з прогнозами сонячної енергії.
// Використовує Retrofit для створення HTTP-запитів до сервера.
// ------------------------------------------------------
class RemoteSolarDataSource {
    // Створюємо екземпляр Retrofit з базовою URL та конвертером Gson
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:5156/")  // Базова адреса API сервера
        .addConverterFactory(GsonConverterFactory.create())  // Конвертер для JSON-об'єктів
        .build()

    // Створюємо екземпляр API-сервісу через інтерфейс SolarApiService
    private val service = retrofit.create(SolarApiService::class.java)

    // Отримуємо список усіх прогнозів сонячної енергії з сервера
    suspend fun fetchAll(): List<SolarForecast> = try {
        service.getForecasts()  // Викликаємо метод API для отримання даних
    } catch (e: Exception) {
        e.printStackTrace()  // Виводимо стек виключення у випадку помилки
        emptyList()  // Повертаємо порожній список у разі помилки
    }

    // Додаємо новий прогноз сонячної енергії на сервер
    suspend fun addForecast(forecast: SolarForecast): SolarForecast? = try {
        val response = service.addForecast(forecast)  // Викликаємо метод API для додавання прогнозу
        if (response.isSuccessful) response.body() else null  // Перевіряємо, чи виконано запит успішно
    } catch (e: Exception) {
        e.printStackTrace()  // Виводимо стек виключення у випадку помилки
        null  // Повертаємо null у разі помилки
    }

    // Оновлюємо існуючий прогноз сонячної енергії на сервері
    suspend fun updateForecast(id: Int, forecast: SolarForecast): SolarForecast? = try {
        val response = service.updateForecast(id, forecast)  // Викликаємо метод API для оновлення прогнозу
        if (response.isSuccessful) response.body() else null  // Перевіряємо, чи виконано запит успішно
    } catch (e: Exception) {
        e.printStackTrace()  // Виводимо стек виключення у випадку помилки
        null  // Повертаємо null у разі помилки
    }

    // Видаляємо прогноз сонячної енергії з сервера за ідентифікатором
    suspend fun deleteForecast(id: Int): Boolean = try {
        val response = service.deleteForecast(id)  // Викликаємо метод API для видалення прогнозу
        response.isSuccessful  // Повертаємо результат успішності видалення
    } catch (e: Exception) {
        e.printStackTrace()  // Виводимо стек виключення у випадку помилки
        false  // Повертаємо false у разі помилки
    }
}