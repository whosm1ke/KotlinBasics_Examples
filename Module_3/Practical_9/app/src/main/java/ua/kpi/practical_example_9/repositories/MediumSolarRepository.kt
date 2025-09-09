package ua.kpi.practical_example_9.repositories

import ua.kpi.practical_example_9.api.FakeApiService
import ua.kpi.practical_example_9.api.SolarApiService
import ua.kpi.practical_example_9.data.SolarPower
import ua.kpi.practical_example_9.sources.MediumLocalSolarDataSource

class MediumSolarRepository(
    private val localDataSource: MediumLocalSolarDataSource,
    private val remoteDataSource: SolarApiService
) {
    // Отримання даних: спочатку локальні, потім додаємо віддалені
    suspend fun getAllPower(): List<SolarPower> {
        val local = localDataSource.getAll()
        val remote = try { remoteDataSource.getForecast() } catch (e: Exception) { emptyList() }
        return (local + remote).distinctBy { it.day }
    }

    suspend fun addPower(power: SolarPower) {
        localDataSource.insert(power)
        try { remoteDataSource.postForecast(power) } catch (_: Exception) { }
    }

    suspend fun updatePower(power: SolarPower) {
        localDataSource.update(power)
        try {
            if (remoteDataSource is FakeApiService) {
                remoteDataSource.updateForecast(power)
            }
        } catch (_: Exception) { }
    }

    suspend fun deletePower(day: String) {
        localDataSource.delete(day)
        try {
            if (remoteDataSource is FakeApiService) {
                remoteDataSource.deleteForecast(day)
            }
        } catch (_: Exception) { }
    }

    fun searchPower(query: String) = localDataSource.search(query)
}