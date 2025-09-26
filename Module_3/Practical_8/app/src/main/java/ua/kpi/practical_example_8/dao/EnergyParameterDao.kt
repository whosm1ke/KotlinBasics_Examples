package ua.kpi.practical_example_8.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ua.kpi.practical_example_8.data.EnergyParameter

/**
 * Інтерфейс Data Access Object (DAO) для роботи з таблицею параметрів енергії в базі даних.
 * Використовується для виконання операцій CRUD (Create, Read) з об'єктами типу EnergyParameter.
 */
@Dao
interface EnergyParameterDao {
    // Вставка нового параметра енергії в таблицю бази даних
    // Метод є асинхронним (suspend), оскільки виконується в фоновому потоці
    @Insert
    suspend fun insert(parameter: EnergyParameter)

    // Отримання всіх параметрів енергії з таблиці бази даних
    // Запит виконується синхронно, повертає список об'єктів типу EnergyParameter
    @Query("SELECT * FROM energy_parameters")
    suspend fun getAll(): List<EnergyParameter>
}