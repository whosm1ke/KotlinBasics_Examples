package ua.kpi.practical_example_9.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import ua.kpi.practical_example_9.data.SolarPower

interface SolarApiService {
    @GET("forecast")
    suspend fun getForecast(): List<SolarPower>

    @POST("forecast")
    suspend fun postForecast(@Body power: SolarPower): SolarPower

    @PUT("forecast")
    suspend fun updateForecast(@Body power: SolarPower): SolarPower
}

