package ua.kpi.practical_example_9.advanced

class SolarRepository(
    private val local: LocalSolarDataSource,
    private val remote: RemoteSolarDataSource
) {

    private val cacheTTL = 5 * 60 * 1000L // кеш 5 хвилин
    private var lastFetchTime = 0L

    /** Основний метод отримання прогнозів з кешем та агрегацією */
    suspend fun getAll(forceRefresh: Boolean = false): Result<List<SolarForecast>> {
        val currentTime = System.currentTimeMillis()
        val localData: List<SolarForecast> = local.getAll()

        if (!forceRefresh && localData.isNotEmpty() && (currentTime - lastFetchTime) < cacheTTL) {
            return Result.success(localData)
        }

        return try {
            // Гарантуємо, що fetchAll() повертає List<SolarForecast>
            val forecasts: List<SolarForecast> = remote.fetchAll().getOrNull() ?: emptyList()
            local.clear()
            forecasts.forEach { local.insert(it) }
            lastFetchTime = currentTime

            val avgPower = forecasts.map { it.predictedPower }.average()
            println("Average predicted power: $avgPower kW")

            Result.success(forecasts) // тип чітко List<SolarForecast>
        } catch (e: Exception) {
            if (localData.isNotEmpty()) Result.success(localData)
            else Result.failure<List<SolarForecast>>(e) // уточнюємо тип
        }
    }

    suspend fun add(forecast: SolarForecast): Result<SolarForecast> = try {
        val added: SolarForecast = remote.addForecast(forecast).getOrNull()
            ?: throw Exception("Failed to add forecast")
        local.insert(added)
        Result.success(added)
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun update(forecast: SolarForecast): Result<SolarForecast> = try {
        val updated: SolarForecast = remote.updateForecast(forecast.id, forecast).getOrNull()
            ?: throw Exception("Failed to update forecast")
        local.update(updated)
        Result.success(updated)
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun delete(id: Int): Result<Boolean> = try {
        val success: Boolean = remote.deleteForecast(id).isSuccess
        if (success) local.delete(id)
        Result.success(success)
    } catch (e: Exception) {
        Result.failure(e)
    }

    fun filterByDay(day: String): List<SolarForecast> {
        return local.filterByDay(day)
    }

    fun sortByPower(descending: Boolean): List<SolarForecast> {
        return local.sortByPower(descending)
    }
}