package ua.kpi.practical_example_10.basic

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

// ----------------------------
// Retrofit API service
// Це інтерфейс визначає контракт для взаємодії з API сонячних станцій
// Використовується Retrofit для створення HTTP-запитів
// ----------------------------
interface SolarApiService {
    // GET запит до ендпоїнту "stations"
    // Повертає список станцій типу List<Station>
    // Використовує suspend-функцію для асинхронного виконання
    @GET("stations")
    suspend fun getStations(): List<Station>

    // POST запит до ендпоїнту "stations"
    // Додає нову станцію, передаючи об'єкт Station у тілі запиту
    // Використовує @Body анотацію для автоматичного серіалізації об'єкта в JSON
    @POST("stations")
    suspend fun addStation(@Body station: Station)
}