package ua.kpi.practical_example_9.medium

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

// ------------------------------------------------------
// Retrofit API сервіс
// ------------------------------------------------------
interface SolarApiService {
    @GET("forecasts")
    suspend fun getForecasts(): List<SolarForecast>

    @POST("forecasts")
    suspend fun addForecast(@Body forecast: SolarForecast): Response<SolarForecast>

    @PUT("forecasts/{id}")
    suspend fun updateForecast(@Path("id") id: Int, @Body forecast: SolarForecast): Response<SolarForecast>

    @DELETE("forecasts/{id}")
    suspend fun deleteForecast(@Path("id") id: Int): Response<Unit>
}
