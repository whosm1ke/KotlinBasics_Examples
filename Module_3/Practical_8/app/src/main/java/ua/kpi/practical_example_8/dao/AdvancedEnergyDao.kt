package ua.kpi.practical_example_8.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import ua.kpi.practical_example_8.data.SolarPower
import ua.kpi.practical_example_8.data.SolarStation
import ua.kpi.practical_example_8.data.SolarVoltage

/**
 * Інтерфейс Data Access Object (DAO) для роботи з базою даних у контексті сонячної енергетики.
 * Використовується для керування таблицями станцій, потужності та напруги.
 */
@Dao
interface AdvancedEnergyDao {

    // ----------------- Робота з SolarStation -----------------
    
    /**
     * Вставляє нову станцію у таблицю solar_station_table.
     * @param station Об'єкт станції для вставки.
     * @return ID вставленого запису.
     */
    @Insert
    suspend fun insertStation(station: SolarStation): Long
    
    /**
     * Отримує всі станції з таблиці solar_station_table, впорядковані за назвою (ASC).
     * @return Список усіх станцій.
     */
    @Query("SELECT * FROM solar_station_table ORDER BY name ASC")
    suspend fun getAllStations(): List<SolarStation>

    // ----------------- Робота з Power -----------------
    
    /**
     * Вставляє дані про потужність у таблицю solar_power_table.
     * @param power Об'єкт потужності для вставки.
     */
    @Insert
    suspend fun insertPower(power: SolarPower)
    
    /**
     * Отримує всі дані про потужність для заданої станції, впорядковані за значенням (DESC).
     * @param stationId ID станції.
     * @return Список записів потужності для заданої станції.
     */
    @Query("SELECT * FROM solar_power_table WHERE stationId = :stationId ORDER BY value DESC")
    suspend fun getPowersForStation(stationId: Int): List<SolarPower>
    
    /**
     * Обчислює середню потужність для заданої станції.
     * @param stationId ID станції.
     * @return Середнє значення потужності або null, якщо записів немає.
     */
    @Query("SELECT AVG(value) FROM solar_power_table WHERE stationId = :stationId")
    suspend fun getAveragePower(stationId: Int): Double?

    // ----------------- Робота з Voltage -----------------
    
    /**
     * Вставляє дані про напругу у таблицю solar_voltage_table.
     * @param voltage Об'єкт напруги для вставки.
     */
    @Insert
    suspend fun insertVoltage(voltage: SolarVoltage)
    
    /**
     * Отримує всі дані про напругу для заданої станції, впорядковані за значенням (DESC).
     * @param stationId ID станції.
     * @return Список записів напруги для заданої станції.
     */
    @Query("SELECT * FROM solar_voltage_table WHERE stationId = :stationId ORDER BY value DESC")
    suspend fun getVoltagesForStation(stationId: Int): List<SolarVoltage>

    // ----------------- Транзакція -----------------
    
    /**
     * Виконує транзакцію вставки станції разом із пов'язаними даними про потужність і напругу.
     * Забезпечує цілісність даних, оскільки всі операції виконуються в одній транзакції.
     * @param station Об'єкт станції для вставки.
     * @param powers Список записів потужності, пов'язаних з цією станцією.
     * @param voltages Список записів напруги, пов'язаних з цією станцією.
     */
    @Transaction
    suspend fun insertStationWithPowerAndVoltage(
        station: SolarStation,
        powers: List<SolarPower>,
        voltages: List<SolarVoltage>
    ) {
        // Вставляємо станцію і отримуємо її ID
        val stationId = insertStation(station).toInt()
        
        // Вставляємо всі записи про потужність з прив'язкою до ID станції
        powers.forEach { insertPower(it.copy(stationId = stationId)) }
        
        // Вставляємо всі записи про напругу з прив'язкою до ID станції
        voltages.forEach { insertVoltage(it.copy(stationId = stationId)) }
    }
}