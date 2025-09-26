package ua.kpi.practical_example_25.data

import kotlinx.coroutines.delay

class AdvancedCalculator {

    /**
     * Симуляція запиту до сервера для розрахунку потужності.
     * Виконується з затримкою, щоб показати асинхронність.
     *
     * @param irradiance значення сонячної інтенсивності (в Вт/м²)
     * @param temperature температура навколишнього середовища (в градусах Цельсія)
     * @param area площа сонячного модуля (в м²)
     * @return потужність у кіловатах (кВт)
     */
    suspend fun calculatePowerAsync(
        irradiance: Double,
        temperature: Double,
        area: Double
    ): Double {
        delay(1500) // імітація запиту на сервер (1.5 сек)

        // Коефіцієнт ефективності сонячного модуля (18%)
        val efficiency = 0.18
        
        // Коефіцієнт температурної залежності (відсоткове зменшення ефективності на градус)
        val tempCoefficient = -0.004
        
        // Розрахунок різниці температури від стандартної (25°C)
        val deltaTemp = temperature - 25.0
        
        // Корегування ефективності з урахуванням температури
        val adjustedEfficiency = efficiency * (1 + tempCoefficient * deltaTemp)
        
        // Розрахунок потужності в ватах
        val powerWatts = irradiance * area * adjustedEfficiency
        
        // Перетворення з Вт у кВт
        return powerWatts / 1000.0
    }
}