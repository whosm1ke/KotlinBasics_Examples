package ua.kpi.practical_example_8.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ua.kpi.practical_example_8.data.Power
import ua.kpi.practical_example_8.data.Temperature
import ua.kpi.practical_example_8.data.Voltage

/**
 * Інтерфейс Data Access Object (DAO) для роботи з таблицями енергетичних даних.
 * Використовується для доступу до бази даних через Room ORM.
 */
@Dao
interface EnergyDao {
    // Вставка даних у відповідні таблиці
    
    /**
     * Вставляє об'єкт Power у таблицю power_table.
     * Функція є асинхронною (suspend), оскільки виконується в фоновому потоці.
     */
    @Insert
    suspend fun insertPower(power: Power)
    
    /**
     * Вставляє об'єкт Voltage у таблицю voltage_table.
     * Функція є асинхронною (suspend), оскільки виконується в фоновому потоці.
     */
    @Insert suspend fun insertVoltage(voltage: Voltage)
    
    /**
     * Вставляє об'єкт Temperature у таблицю temperature_table.
     * Функція є асинхронною (suspend), оскільки виконується в фоновому потоці.
     */
    @Insert suspend fun insertTemperature(temp: Temperature)

    // Отримання всіх записів з таблиць
    
    /**
     * Отримує всі записи з таблиці power_table.
     * Повертає список об'єктів типу Power.
     * Функція є асинхронною (suspend), оскільки виконується в фоновому потоці.
     */
    @Query("SELECT * FROM power_table") suspend fun getAllPower(): List<Power>
    
    /**
     * Отримує всі записи з таблиці voltage_table.
     * Повертає список об'єктів типу Voltage.
     * Функція є асинхронною (suspend), оскільки виконується в фоновому потоці.
     */
    @Query("SELECT * FROM voltage_table") suspend fun getAllVoltage(): List<Voltage>
    
    /**
     * Отримує всі записи з таблиці temperature_table.
     * Повертає список об'єктів типу Temperature.
     * Функція є асинхронною (suspend), оскільки виконується в фоновому потоці.
     */
    @Query("SELECT * FROM temperature_table") suspend fun getAllTemperature(): List<Temperature>

    // Оновлення записів
    
    /**
     * Оновлює запис у таблиці power_table на основі переданого об'єкта Power.
     * Функція є асинхронною (suspend), оскільки виконується в фоновому потоці.
     */
    @Update suspend fun updatePower(power: Power)
    
    /**
     * Оновлює запис у таблиці voltage_table на основі переданого об'єкта Voltage.
     * Функція є асинхронною (suspend), оскільки виконується в фоновому потоці.
     */
    @Update suspend fun updateVoltage(voltage: Voltage)
    
    /**
     * Оновлює запис у таблиці temperature_table на основі переданого об'єкта Temperature.
     * Функція є асинхронною (suspend), оскільки виконується в фоновому потоці.
     */
    @Update
    suspend fun updateTemperature(temp: Temperature)

    // Видалення записів
    
    /**
     * Видаляє запис із таблиці power_table на основі переданого об'єкта Power.
     * Функція є асинхронною (suspend), оскільки виконується в фоновому потоці.
     */
    @Delete suspend fun deletePower(power: Power)
    
    /**
     * Видаляє запис із таблиці voltage_table на основі переданого об'єкта Voltage.
     * Функція є асинхронною (suspend), оскільки виконується в фоновому потоці.
     */
    @Delete suspend fun deleteVoltage(voltage: Voltage)
    
    /**
     * Видаляє запис із таблиці temperature_table на основі переданого об'єкта Temperature.
     * Функція є асинхронною (suspend), оскільки виконується в фоновому потоці.
     */
    @Delete
    suspend fun deleteTemperature(temp: Temperature)

    // Пошук записів за певним значенням
    
    /**
     * Шукає записи у таблиці power_table, де поле value дорівнює переданому значенню.
     * Повертає список об'єктів типу Power.
     * Функція є асинхронною (suspend), оскільки виконується в фоновому потоці.
     */
    @Query("SELECT * FROM power_table WHERE value = :value") suspend fun findPowerByValue(value: Double): List<Power>
}