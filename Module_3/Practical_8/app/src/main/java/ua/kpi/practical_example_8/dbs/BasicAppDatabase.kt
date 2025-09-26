package ua.kpi.practical_example_8.dbs

import androidx.room.Database
import androidx.room.RoomDatabase
import ua.kpi.practical_example_8.dao.EnergyParameterDao
import ua.kpi.practical_example_8.data.EnergyParameter

// Абстрактний клас бази даних, що наслідується від RoomDatabase
// Використовується для створення та керування базою даних за допомогою Room
@Database(entities = [EnergyParameter::class], version = 1, exportSchema = false)
abstract class BasicAppDatabase : RoomDatabase() {
    // Абстрактний метод, що повертає DAO для роботи з таблицею EnergyParameter
    // DAO (Data Access Object) надає методи для доступу до даних у базі даних
    abstract fun energyParameterDao(): EnergyParameterDao
}