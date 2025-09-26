package ua.kpi.practical_example_9.medium

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

// ------------------------------------------------------
// Retrofit API сервіс для роботи з прогнозами сонячної енергії
// Використовується для взаємодії з сервером через REST API
// ------------------------------------------------------
interface SolarApiService {
    // Отримання списку всіх прогнозів сонячної енергії
    @GET("forecasts")
    suspend fun getForecasts(): List<SolarForecast>

    // Додавання нового прогнозу сонячної енергії
    @POST("forecasts")
    suspend fun addForecast(@Body forecast: SolarForecast): Response<SolarForecast>

    // Оновлення існуючого прогнозу за його ID
    @PUT("forecasts/{id}")
    suspend fun updateForecast(@Path("id") id: Int, @Body forecast: SolarForecast): Response<SolarForecast>

    // Видалення прогнозу за його ID
    @DELETE("forecasts/{id}")
    suspend fun deleteForecast(@Path("id") id: Int): Response<Unit>
}