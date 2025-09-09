package ua.kpi.practical_example_8.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import ua.kpi.practical_example_8.data.SolarPower
import ua.kpi.practical_example_8.data.SolarStation
import ua.kpi.practical_example_8.data.SolarVoltage

@Dao
interface AdvancedEnergyDao {

    // ----------------- SolarStation -----------------
    @Insert
    suspend fun insertStation(station: SolarStation): Long
    @Query("SELECT * FROM solar_station_table ORDER BY name ASC")
    suspend fun getAllStations(): List<SolarStation>

    // ----------------- Power -----------------
    @Insert
    suspend fun insertPower(power: SolarPower)
    @Query("SELECT * FROM solar_power_table WHERE stationId = :stationId ORDER BY value DESC")
    suspend fun getPowersForStation(stationId: Int): List<SolarPower>
    @Query("SELECT AVG(value) FROM solar_power_table WHERE stationId = :stationId")
    suspend fun getAveragePower(stationId: Int): Double?

    // ----------------- Voltage -----------------
    @Insert
    suspend fun insertVoltage(voltage: SolarVoltage)
    @Query("SELECT * FROM solar_voltage_table WHERE stationId = :stationId ORDER BY value DESC")
    suspend fun getVoltagesForStation(stationId: Int): List<SolarVoltage>

    // ----------------- Транзакція -----------------
    @Transaction
    suspend fun insertStationWithPowerAndVoltage(
        station: SolarStation,
        powers: List<SolarPower>,
        voltages: List<SolarVoltage>
    ) {
        val stationId = insertStation(station).toInt()
        powers.forEach { insertPower(it.copy(stationId = stationId)) }
        voltages.forEach { insertVoltage(it.copy(stationId = stationId)) }
    }
}