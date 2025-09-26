package ua.kpi.practical_example_12.advanced

import retrofit2.http.GET
import retrofit2.http.Path

// === Retrofit API-сервіс для отримання прогнозу сонячних панелей ===
interface ForecastApiService {
    
    // Отримання прогнозу на сьогоднішній день
    // Викликає endpoint "forecast/today"
    // Повертає об'єкт типу SolarPanelForecast
    @GET("forecast/today")
    suspend fun getTodayForecast(): SolarPanelForecast

    // Отримання помилки за кодом
    // Викликає endpoint "forecast/error/{code}"
    // Параметр {code} передається через@Path анотацію
    // Повертає об'єкт типу Message
    @GET("forecast/error/{code}")
    suspend fun getError(@Path("code") code: Int): Message

}