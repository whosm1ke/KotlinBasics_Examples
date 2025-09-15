package ua.kpi.practical_example_12.medium

import retrofit2.http.GET
import retrofit2.http.Path

// === Retrofit API-сервіс ===
interface ForecastApiService {
    @GET("forecast/today")
    suspend fun getTodayForecast(): SolarPanelForecast

    @GET("forecast/error/{code}")
    suspend fun getError(@Path("code") code: Int): Message

}