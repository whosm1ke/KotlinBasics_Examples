package ua.kpi.practical_example_9.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import ua.kpi.practical_example_9.data.SolarPower

interface AdvancedSolarApiService {
    @GET("forecast")
    suspend fun getForecast(): List<SolarPower>

    @POST("forecast")
    suspend fun postForecast(@Body power: SolarPower): SolarPower

    @DELETE("forecast/{day}")
    suspend fun deleteForecast(@Path("day") day: String)
}


object RetrofitClient {
    val api: AdvancedSolarApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://your.api.mock/") // Можна замінити на реальний URL або локальний сервер
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AdvancedSolarApiService::class.java)
    }
}
