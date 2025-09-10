package ua.kpi.practical_example_9.advanced

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteSolarDataSource {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:5156/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(SolarApiService::class.java)

    /** Fetch з обробкою помилок */
    suspend fun fetchAll(): Result<List<SolarForecast>> = try {
        Result.success(service.getForecasts())
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun addForecast(forecast: SolarForecast): Result<SolarForecast> = try {
        val response = service.addForecast(forecast)
        if (response.isSuccessful) Result.success(response.body()!!)
        else Result.failure(Exception("Add failed: ${response.code()}"))
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun updateForecast(id: Int, forecast: SolarForecast): Result<SolarForecast> = try {
        val response = service.updateForecast(id, forecast)
        if (response.isSuccessful) Result.success(response.body()!!)
        else Result.failure(Exception("Update failed: ${response.code()}"))
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun deleteForecast(id: Int): Result<Boolean> = try {
        val response = service.deleteForecast(id)
        if (response.isSuccessful) Result.success(true)
        else Result.failure(Exception("Delete failed: ${response.code()}"))
    } catch (e: Exception) {
        Result.failure(e)
    }
}