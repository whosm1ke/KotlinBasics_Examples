package ua.kpi.practical_example_9.repositories

import ua.kpi.practical_example_9.api.AdvancedSolarApiService
import ua.kpi.practical_example_9.api.RetrofitClient
import ua.kpi.practical_example_9.data.SolarPower
import ua.kpi.practical_example_9.sources.AdvancedLocalSolarDataSource

class AdvancedSolarRepository(
    private val localDataSource: AdvancedLocalSolarDataSource,
) {
    private val remoteDataSource = RetrofitClient.api
    // Кешування останніх даних
    private var cachedData: List<SolarPower>? = null

    suspend fun getAllPower(forceRefresh: Boolean = false): List<SolarPower> {
        if (!forceRefresh && cachedData != null) return cachedData!!

        return try {
            val local = localDataSource.getAll()
            val remote = remoteDataSource.getForecast()
            val combined = (local + remote).distinctBy { it.day }
            cachedData = combined.sortedBy { it.day }
            cachedData!!
        } catch (e: Exception) {
            localDataSource.getAll()
        }
    }

    suspend fun addPower(power: SolarPower) {
        localDataSource.insert(power)
        try { remoteDataSource.postForecast(power) } catch (_: Exception) { }
        cachedData = null
    }

    suspend fun deletePower(day: String) {
        localDataSource.delete(day)
        try { remoteDataSource.deleteForecast(day) } catch (_: Exception) { }
        cachedData = null
    }

    suspend fun updatePower(power: SolarPower) {
        localDataSource.update(power)

    }

    suspend fun calculateAveragePower(): Double {
        val data = getAllPower()
        return if (data.isEmpty()) 0.0 else data.map { it.power }.average()
    }

    suspend fun filterAndSort(query: String = "", ascending: Boolean = true): List<SolarPower> {
        val data = getAllPower()
        val filtered = if (query.isBlank()) data else data.filter { it.day.contains(query) }
        return if (ascending) filtered.sortedBy { it.power } else filtered.sortedByDescending { it.power }
    }
}