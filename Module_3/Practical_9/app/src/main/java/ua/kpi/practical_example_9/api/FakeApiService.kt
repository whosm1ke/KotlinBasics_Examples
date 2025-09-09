package ua.kpi.practical_example_9.api

import kotlinx.coroutines.delay
import ua.kpi.practical_example_9.data.SolarPower

class FakeApiService : SolarApiService {
    private val remoteData = mutableListOf(
        SolarPower("2025-09-10", 120.0),
        SolarPower("2025-09-11", 130.5)
    )

    override suspend fun getForecast(): List<SolarPower> {
        delay(1000)
        return remoteData.toList()
    }

    override suspend fun postForecast(power: SolarPower): SolarPower {
        delay(500)
        remoteData.add(power)
        return power
    }

    suspend fun deleteForecast(day: String) {
        delay(500) // імітація мережевої затримки
        remoteData.removeAll { it.day == day }
    }


    override suspend fun updateForecast(updatedPower: SolarPower): SolarPower {
        delay(500) // імітація мережевої затримки
        val index = remoteData.indexOfFirst { it.day == updatedPower.day }
        if (index != -1) {
            remoteData[index] = updatedPower
        } else {
            remoteData.add(updatedPower) // якщо немає, додаємо як новий
        }
        return updatedPower
    }
}