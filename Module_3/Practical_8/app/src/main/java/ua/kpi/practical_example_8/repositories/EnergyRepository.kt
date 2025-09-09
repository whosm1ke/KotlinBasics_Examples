package ua.kpi.practical_example_8.repositories

import ua.kpi.practical_example_8.dao.EnergyDao
import ua.kpi.practical_example_8.data.Power
import ua.kpi.practical_example_8.data.Temperature
import ua.kpi.practical_example_8.data.Voltage

class EnergyRepository(private val dao: EnergyDao) {
    suspend fun insertPower(power: Power) = dao.insertPower(power)
    suspend fun insertVoltage(voltage: Voltage) = dao.insertVoltage(voltage)
    suspend fun insertTemperature(temp: Temperature) = dao.insertTemperature(temp)

    suspend fun getAllPower() = dao.getAllPower()
    suspend fun getAllVoltage() = dao.getAllVoltage()
    suspend fun getAllTemperature() = dao.getAllTemperature()
}