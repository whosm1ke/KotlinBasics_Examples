package ua.kpi.practical_example_25.data

import kotlinx.coroutines.delay

class AdvancedCalculator {

    /**
     * Симуляція запиту до сервера для розрахунку потужності.
     * Виконується з затримкою, щоб показати асинхронність.
     */
    suspend fun calculatePowerAsync(
        irradiance: Double,
        temperature: Double,
        area: Double
    ): Double {
        delay(1500) // імітація запиту на сервер (1.5 сек)

        val efficiency = 0.18
        val tempCoefficient = -0.004
        val deltaTemp = temperature - 25.0
        val adjustedEfficiency = efficiency * (1 + tempCoefficient * deltaTemp)
        val powerWatts = irradiance * area * adjustedEfficiency

        return powerWatts / 1000.0
    }
}