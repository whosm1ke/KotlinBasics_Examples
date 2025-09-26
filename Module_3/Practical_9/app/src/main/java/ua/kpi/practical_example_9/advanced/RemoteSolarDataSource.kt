package ua.kpi.practical_example_9.advanced

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Клас, що відповідає за взаємодію з віддаленим API для отримання та керування даними про сонячне опромінення.
 * Використовує Retrofit для HTTP-запитів і Gson для серіалізації/десеріалізації JSON.
 */
class RemoteSolarDataSource {
    // Створюємо екземпляр Retrofit з базовою URL та конвертером Gson
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:5156/")  // Базова URL API (для Android Emulator)
        .addConverterFactory(GsonConverterFactory.create())  // Додаємо конвертер для JSON
        .build()

    // Створюємо екземпляр сервісу через Retrofit, що дозволяє виконувати HTTP-запити
    private val service = retrofit.create(SolarApiService::class.java)

    /**
     * Асинхронно отримує список прогнозів сонячного опромінення.
     * @return Result<List<SolarForecast>> - успішний результат з даними або помилка.
     */
    suspend fun fetchAll(): Result<List<SolarForecast>> = try {
        // Викликаємо метод API для отримання всіх прогнозів
        Result.success(service.getForecasts())
    } catch (e: Exception) {
        // У разі виникнення помилки повертаємо її у вигляді Result.failure
        Result.failure(e)
    }

    /**
     * Додає новий прогноз сонячного опромінення до API.
     * @param forecast Об'єкт прогнозу, який потрібно додати.
     * @return Result<SolarForecast> - успішний результат з доданим об'єктом або помилка.
     */
    suspend fun addForecast(forecast: SolarForecast): Result<SolarForecast> = try {
        // Викликаємо метод API для додавання прогнозу
        val response = service.addForecast(forecast)
        if (response.isSuccessful) {
            // Якщо запит успішний, повертаємо тіло відповіді
            Result.success(response.body()!!)
        } else {
            // Якщо не успішно, повертаємо помилку з кодом статусу
            Result.failure(Exception("Add failed: ${response.code()}"))
        }
    } catch (e: Exception) {
        // У разі виникнення помилки повертаємо її у вигляді Result.failure
        Result.failure(e)
    }

    /**
     * Оновлює існуючий прогноз сонячного опромінення за ID.
     * @param id Ідентифікатор прогнозу для оновлення.
     * @param forecast Нові дані прогнозу.
     * @return Result<SolarForecast> - успішний результат з оновленим об'єктом або помилка.
     */
    suspend fun updateForecast(id: Int, forecast: SolarForecast): Result<SolarForecast> = try {
        // Викликаємо метод API для оновлення прогнозу за ID
        val response = service.updateForecast(id, forecast)
        if (response.isSuccessful) {
            // Якщо запит успішний, повертаємо тіло відповіді
            Result.success(response.body()!!)
        } else {
            // Якщо не успішно, повертаємо помилку з кодом статусу
            Result.failure(Exception("Update failed: ${response.code()}"))
        }
    } catch (e: Exception) {
        // У разі виникнення помилки повертаємо її у вигляді Result.failure
        Result.failure(e)
    }

    /**
     * Видаляє прогноз сонячного опромінення за ID.
     * @param id Ідентифікатор прогнозу для видалення.
     * @return Result<Boolean> - успішний результат (true) або помилка.
     */
    suspend fun deleteForecast(id: Int): Result<Boolean> = try {
        // Викликаємо метод API для видалення прогнозу за ID
        val response = service.deleteForecast(id)
        if (response.isSuccessful) {
            // Якщо запит успішний, повертаємо true
            Result.success(true)
        } else {
            // Якщо не успішно, повертаємо помилку з кодом статусу
            Result.failure(Exception("Delete failed: ${response.code()}"))
        }
    } catch (e: Exception) {
        // У разі виникнення помилки повертаємо її у вигляді Result.failure
        Result.failure(e)
    }
}