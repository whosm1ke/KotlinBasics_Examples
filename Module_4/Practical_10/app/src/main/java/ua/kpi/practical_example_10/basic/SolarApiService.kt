package ua.kpi.practical_example_10.basic

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

// ----------------------------
// Retrofit API service
// ----------------------------
interface SolarApiService {
    @GET("stations")
    suspend fun getStations(): List<Station>

    @POST("stations")
    suspend fun addStation(@Body station: Station)
}