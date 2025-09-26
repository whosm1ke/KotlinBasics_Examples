package ua.kpi.practical_example_12.basic

import retrofit2.http.GET

// === Retrofit API-сервіс для отримання прогнозу сонячних панелей ===
interface ForecastApiService {
    // Оголошуємо GET-запит до ендпоінту "forecast/today"
    // Метод є асинхронним (suspend), що дозволяє використовувати його в корутинах
    // Повертає об'єкт типу SolarPanelForecast, який містить дані про прогноз
    @GET("forecast/today")
    suspend fun getTodayForecast(): SolarPanelForecast
}