package ua.kpi.practical_example_8.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ua.kpi.practical_example_8.data.EnergyParameter

@Dao
interface EnergyParameterDao {
    // Вставка нового параметра
    @Insert
    suspend fun insert(parameter: EnergyParameter)

    // Отримання всіх параметрів
    @Query("SELECT * FROM energy_parameters")
    suspend fun getAll(): List<EnergyParameter>
}
