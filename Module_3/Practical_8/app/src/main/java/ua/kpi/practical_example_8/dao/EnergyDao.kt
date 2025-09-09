package ua.kpi.practical_example_8.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ua.kpi.practical_example_8.data.Power
import ua.kpi.practical_example_8.data.Temperature
import ua.kpi.practical_example_8.data.Voltage

@Dao
interface EnergyDao {
    // Вставка
    @Insert
    suspend fun insertPower(power: Power)
    @Insert suspend fun insertVoltage(voltage: Voltage)
    @Insert suspend fun insertTemperature(temp: Temperature)

    // Отримання всіх
    @Query("SELECT * FROM power_table") suspend fun getAllPower(): List<Power>
    @Query("SELECT * FROM voltage_table") suspend fun getAllVoltage(): List<Voltage>
    @Query("SELECT * FROM temperature_table") suspend fun getAllTemperature(): List<Temperature>

    // Оновлення
    @Update suspend fun updatePower(power: Power)
    @Update suspend fun updateVoltage(voltage: Voltage)
    @Update
    suspend fun updateTemperature(temp: Temperature)

    // Видалення
    @Delete suspend fun deletePower(power: Power)
    @Delete suspend fun deleteVoltage(voltage: Voltage)
    @Delete
    suspend fun deleteTemperature(temp: Temperature)

    // Пошук за значенням
    @Query("SELECT * FROM power_table WHERE value = :value") suspend fun findPowerByValue(value: Double): List<Power>
}
