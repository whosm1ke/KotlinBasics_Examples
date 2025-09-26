package ua.kpi.practical_example_8.repositories

import ua.kpi.practical_example_8.dao.EnergyDao
import ua.kpi.practical_example_8.data.Power
import ua.kpi.practical_example_8.data.Temperature
import ua.kpi.practical_example_8.data.Voltage

class EnergyRepository(private val dao: EnergyDao) {
    // Вставляє дані про потужність у базу даних
    suspend fun insertPower(power: Power) = dao.insertPower(power)
    
    // Вставляє дані про напругу у базу даних
    suspend fun insertVoltage(voltage: Voltage) = dao.insertVoltage(voltage)
    
    // Вставляє дані про температуру у базу даних
    suspend fun insertTemperature(temp: Temperature) = dao.insertTemperature(temp)

    // Отримує всі записи про потужність з бази даних
    suspend fun getAllPower() = dao.getAllPower()
    
    // Отримує всі записи про напругу з бази даних
    suspend fun getAllVoltage() = dao.getAllVoltage()
    
    // Отримує всі записи про температуру з бази даних
    suspend fun getAllTemperature() = dao.getAllTemperature()
}