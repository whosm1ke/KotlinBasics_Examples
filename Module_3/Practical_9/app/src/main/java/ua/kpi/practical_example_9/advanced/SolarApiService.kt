package ua.kpi.practical_example_9.advanced

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

/**
 * Інтерфейс API сервісу для роботи з прогнозами сонячної енергії.
 * Використовує Retrofit для створення HTTP запитів до сервера.
 */
interface SolarApiService {
    /**
     * Отримання списку всіх прогнозів сонячної енергії.
     * Виконує GET запит до шляху "forecasts".
     * @return Список об'єктів SolarForecast
     */
    @GET("forecasts")
    suspend fun getForecasts(): List<SolarForecast>

    /**
     * Додавання нового прогнозу сонячної енергії.
     * Виконує POST запит до шляху "forecasts".
     * @param forecast Об'єкт прогнозу, який потрібно додати
     * @return Response з об'єктом SolarForecast або помилкою
     */
    @POST("forecasts")
    suspend fun addForecast(@Body forecast: SolarForecast): Response<SolarForecast>

    /**
     * Оновлення існуючого прогнозу сонячної енергії.
     * Виконує PUT запит до шляху "forecasts/{id}", де {id} - ідентифікатор прогнозу.
     * @param id Ідентифікатор прогнозу для оновлення
     * @param forecast Нові дані прогнозу
     * @return Response з об'єктом SolarForecast або помилкою
     */
    @PUT("forecasts/{id}")
    suspend fun updateForecast(@Path("id") id: Int, @Body forecast: SolarForecast): Response<SolarForecast>

    /**
     * Видалення прогнозу сонячної енергії.
     * Виконує DELETE запит до шляху "forecasts/{id}", де {id} - ідентифікатор прогнозу.
     * @param id Ідентифікатор прогнозу для видалення
     * @return Response з типом Unit або помилкою
     */
    @DELETE("forecasts/{id}")
    suspend fun deleteForecast(@Path("id") id: Int): Response<Unit>
}