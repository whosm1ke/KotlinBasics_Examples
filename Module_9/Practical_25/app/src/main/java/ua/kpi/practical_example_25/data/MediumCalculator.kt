package ua.kpi.practical_example_25.data

class MediumCalculator {

    /**
     * Розрахунок прогнозованої потужності сонячної електростанції.
     *
     * @param irradiance сонячна радіація (Вт/м²) - кількість світла, що падає на сонячну панель
     * @param temperature температура навколишнього середовища (°C) - впливає на ефективність панелей
     * @param area площа сонячних панелей (м²) - загальна площа активної поверхні панелей
     * @return прогнозована потужність у кіловатах (кВт) - результат розрахунку потужності
     */
    fun calculatePower(irradiance: Double, temperature: Double, area: Double): Double {
        // Коефіцієнт ефективності сонячних панелей (18%)
        val efficiency = 0.18
        
        // Коефіцієнт температурного впливу: -0.4% на градус вище 25°C
        val tempCoefficient = -0.004
        
        // Обчислення різниці температури від стандартної (25°C)
        val deltaTemp = temperature - 25.0
        
        // Коригування ефективності з урахуванням температури
        val adjustedEfficiency = efficiency * (1 + tempCoefficient * deltaTemp)
        
        // Розрахунок потужності в ватах: радіація × площа × ефективність
        val powerWatts = irradiance * area * adjustedEfficiency
        
        // Перетворення ватів у кіловати (1 кВт = 1000 Вт)
        return powerWatts / 1000.0
    }
}