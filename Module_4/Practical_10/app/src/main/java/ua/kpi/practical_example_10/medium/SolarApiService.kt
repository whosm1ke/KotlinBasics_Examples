package ua.kpi.practical_example_10.medium

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import ua.kpi.practical_example_10.basic.Station

/**
 * Інтерфейс для роботи з API сонячних станцій.
 * Використовує Retrofit для створення HTTP-запитів до сервера.
 */
interface SolarApiService {
    /**
     * Отримати список всіх сонячних станцій.
     * Запит типу GET до шляху /stations
     * @return Список об'єктів Station
     */
    @GET("stations")
    suspend fun getStations(): List<Station>

    /**
     * Отримати інформацію про конкретну станцію за її ID.
     * Запит типу GET до шляху /stations/{id}
     * @param id Ідентифікатор станції
     * @return Об'єкт Station з відповідним ID
     */
    @GET("stations/{id}")
    suspend fun getStation(@Path("id") id: Int): Station

    /**
     * Додати нову станцію на сервер.
     * Запит типу POST до шляху /stations
     * @param station Об'єкт станції, який потрібно додати
     */
    @POST("stations")
    suspend fun addStation(@Body station: Station)

    /**
     * Отримати прогнози погоди для конкретної станції.
     * Запит типу GET до шляху /stations/{id}/forecasts
     * @param stationId Ідентифікатор станції
     * @return Список об'єктів Forecast
     */
    @GET("stations/{id}/forecasts")
    suspend fun getForecasts(@Path("id") stationId: Int): List<Forecast>

    /**
     * Отримати статистичні дані прогнозів для конкретної станції.
     * Запит типу GET до шляху /stations/{id}/forecasts/stats
     * @param stationId Ідентифікатор станції
     * @return Об'єкт ForecastStats зі статистикою
     */
    @GET("stations/{id}/forecasts/stats")
    suspend fun getForecastStats(@Path("id") stationId: Int): ForecastStats

    /**
     * Згенерувати прогнози погоди для конкретної станції на задану кількість днів.
     * Запит типу GET до шляху /stations/{id}/forecasts/generate/{days}
     * @param stationId Ідентифікатор станції
     * @param days Кількість днів для генерації прогнозів
     * @return Список об'єктів Forecast згенерованих прогнозів
     */
    @GET("stations/{id}/forecasts/generate/{days}")
    suspend fun generateForecasts(@Path("id") stationId: Int, @Path("days") days: Int): List<Forecast>

}