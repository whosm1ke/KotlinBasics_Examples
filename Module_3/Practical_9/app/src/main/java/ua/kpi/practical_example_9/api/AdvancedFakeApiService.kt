package ua.kpi.practical_example_9.api

import kotlinx.coroutines.delay
import ua.kpi.practical_example_9.data.SolarPower

class AdvancedFakeApiService : AdvancedSolarApiService {
    private val remoteData = mutableListOf(
        SolarPower("2025-09-10", 120.0),
        SolarPower("2025-09-11", 130.5),
        SolarPower("2025-09-12", 110.0)
    )

    override suspend fun getForecast(): List<SolarPower> {
        delay(1000) // симуляція мережі
        return remoteData.toList()
    }

    override suspend fun postForecast(power: SolarPower): SolarPower {
        delay(500)
        remoteData.add(power)
        return power
    }

    override suspend fun deleteForecast(day: String) {
        delay(500)
        remoteData.removeAll { it.day == day }
    }
}