package ua.kpi.practical_example_8.repositories

import ua.kpi.practical_example_8.dao.AdvancedEnergyDao
import ua.kpi.practical_example_8.data.SolarPower
import ua.kpi.practical_example_8.data.SolarStation
import ua.kpi.practical_example_8.data.SolarVoltage

class AdvancedEnergyRepository(private val dao: AdvancedEnergyDao) {
    // Вставляє дані про станцію разом із потужністю та напругою у базу даних
    suspend fun insertStationWithData(station: SolarStation, powers: List<SolarPower>, voltages: List<SolarVoltage>) =
        dao.insertStationWithPowerAndVoltage(station, powers, voltages)

    // Отримує список всіх сонячних станцій з бази даних
    suspend fun getAllStations() = dao.getAllStations()

    // Отримує список потужностей для заданої станції за її ідентифікатором
    suspend fun getPowersForStation(stationId: Int) = dao.getPowersForStation(stationId)

    // Отримує список напруг для заданої станції за її ідентифікатором
    suspend fun getVoltagesForStation(stationId: Int) = dao.getVoltagesForStation(stationId)

    // Обчислює середню потужність для заданої станції за її ідентифікатором
    suspend fun getAveragePower(stationId: Int) = dao.getAveragePower(stationId)
}