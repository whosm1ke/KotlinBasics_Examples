package ua.kpi.practical_example_25.data

class BasicCalculator {
    /**
     * Функція для розрахунку прогнозованої потужності сонячної електростанції.
     *
     * @param irradiance сонячна радіація (Вт/м²)
     * @param temperature температура навколишнього середовища (°C)
     * @param area площа сонячних панелей (м²)
     * @return прогнозована потужність у кіловатах (кВт)
     */
    fun calculatePower(irradiance: Double, temperature: Double, area: Double): Double {
        // ККД панелей за стандартних умов (припустимо 18%)
        val efficiency = 0.18

        // Температурний коефіцієнт (зменшення ефективності при перегріванні)
        val tempCoefficient = -0.004 // -0.4% на градус вище 25°C

        // Різниця температури відносно стандарту (25°C)
        val deltaTemp = temperature - 25.0

        // Фактичний ККД з урахуванням температури
        val adjustedEfficiency = efficiency * (1 + tempCoefficient * deltaTemp)

        // Розрахунок потужності (Вт)
        val powerWatts = irradiance * area * adjustedEfficiency

        // Переведення у кіловати
        return powerWatts / 1000.0
    }
}

