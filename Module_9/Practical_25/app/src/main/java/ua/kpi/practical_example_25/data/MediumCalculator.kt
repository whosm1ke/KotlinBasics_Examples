package ua.kpi.practical_example_25.data

class MediumCalculator {

    /**
     * Розрахунок прогнозованої потужності сонячної електростанції.
     *
     * @param irradiance сонячна радіація (Вт/м²)
     * @param temperature температура навколишнього середовища (°C)
     * @param area площа сонячних панелей (м²)
     * @return прогнозована потужність у кіловатах (кВт)
     */
    fun calculatePower(irradiance: Double, temperature: Double, area: Double): Double {
        val efficiency = 0.18
        val tempCoefficient = -0.004 // -0.4% на градус вище 25°C
        val deltaTemp = temperature - 25.0
        val adjustedEfficiency = efficiency * (1 + tempCoefficient * deltaTemp)
        val powerWatts = irradiance * area * adjustedEfficiency
        return powerWatts / 1000.0
    }
}

