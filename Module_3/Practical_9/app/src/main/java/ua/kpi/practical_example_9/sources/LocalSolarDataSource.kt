package ua.kpi.practical_example_9.sources

import ua.kpi.practical_example_9.data.SolarPower

class LocalSolarDataSource {
    private val _powerData = mutableListOf<SolarPower>()

    // Метод для отримання всіх записів
    fun getAll(): List<SolarPower> = _powerData.toList()

    // Метод для додавання нового запису
    fun insert(power: SolarPower) {
        _powerData.add(power)
    }
}

