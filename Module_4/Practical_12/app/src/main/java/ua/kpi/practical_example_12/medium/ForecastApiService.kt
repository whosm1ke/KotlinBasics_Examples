package ua.kpi.practical_example_12.medium

import retrofit2.http.GET
import retrofit2.http.Path

// === Retrofit API-сервіс для отримання прогнозу сонячних панелей ===
interface ForecastApiService {
    // Отримати прогноз на сьогоднішній день
    @GET("forecast/today")
    suspend fun getTodayForecast(): SolarPanelForecast

    // Отримати повідомлення про помилку за вказаним кодом
    @GET("forecast/error/{code}")
    suspend fun getError(@Path("code") code: Int): Message

}