package ua.kpi.practical_example_9.medium

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
// ------------------------------------------------------
// Remote Data Source через Retrofit
// ------------------------------------------------------
class RemoteSolarDataSource {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:5156/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(SolarApiService::class.java)

    suspend fun fetchAll(): List<SolarForecast> = try {
        service.getForecasts()
    } catch (e: Exception) {
        e.printStackTrace()
        emptyList()
    }

    suspend fun addForecast(forecast: SolarForecast): SolarForecast? = try {
        val response = service.addForecast(forecast)
        if (response.isSuccessful) response.body() else null
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }

    suspend fun updateForecast(id: Int, forecast: SolarForecast): SolarForecast? = try {
        val response = service.updateForecast(id, forecast)
        if (response.isSuccessful) response.body() else null
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }

    suspend fun deleteForecast(id: Int): Boolean = try {
        val response = service.deleteForecast(id)
        response.isSuccessful
    } catch (e: Exception) {
        e.printStackTrace()
        false
    }
}