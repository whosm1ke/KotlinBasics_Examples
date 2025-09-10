package ua.kpi.practical_example_10.medium

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import ua.kpi.practical_example_10.basic.Station

interface SolarApiService {
    @GET("stations")
    suspend fun getStations(): List<Station>

    @GET("stations/{id}")
    suspend fun getStation(@Path("id") id: Int): Station

    @POST("stations")
    suspend fun addStation(@Body station: Station)

    @GET("stations/{id}/forecasts")
    suspend fun getForecasts(@Path("id") stationId: Int): List<Forecast>

    @GET("stations/{id}/forecasts/stats")
    suspend fun getForecastStats(@Path("id") stationId: Int): ForecastStats

    @GET("stations/{id}/forecasts/generate/{days}")
    suspend fun generateForecasts(@Path("id") stationId: Int, @Path("days") days: Int): List<Forecast>

}

