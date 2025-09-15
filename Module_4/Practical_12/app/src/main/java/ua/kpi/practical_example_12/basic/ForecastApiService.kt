package ua.kpi.practical_example_12.basic

import retrofit2.http.GET

// === Retrofit API-сервіс ===
interface ForecastApiService {
    @GET("forecast/today")
    suspend fun getTodayForecast(): SolarPanelForecast
}