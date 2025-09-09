package ua.kpi.practical_example_8.repositories

import ua.kpi.practical_example_8.dao.AdvancedEnergyDao
import ua.kpi.practical_example_8.data.SolarPower
import ua.kpi.practical_example_8.data.SolarStation
import ua.kpi.practical_example_8.data.SolarVoltage

class AdvancedEnergyRepository(private val dao: AdvancedEnergyDao) {
    suspend fun insertStationWithData(station: SolarStation, powers: List<SolarPower>, voltages: List<SolarVoltage>) =
        dao.insertStationWithPowerAndVoltage(station, powers, voltages)

    suspend fun getAllStations() = dao.getAllStations()
    suspend fun getPowersForStation(stationId: Int) = dao.getPowersForStation(stationId)
    suspend fun getVoltagesForStation(stationId: Int) = dao.getVoltagesForStation(stationId)
    suspend fun getAveragePower(stationId: Int) = dao.getAveragePower(stationId)
}