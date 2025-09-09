package ua.kpi.practical_example_9.repositories

import ua.kpi.practical_example_9.data.SolarPower
import ua.kpi.practical_example_9.sources.LocalSolarDataSource

class SolarRepository(private val localDataSource: LocalSolarDataSource) {

    // Метод отримання всіх даних
    fun getAllPower(): List<SolarPower> = localDataSource.getAll()

    // Метод додавання нового запису
    fun addPower(power: SolarPower) = localDataSource.insert(power)
}

