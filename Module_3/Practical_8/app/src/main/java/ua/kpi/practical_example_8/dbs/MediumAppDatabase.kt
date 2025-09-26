package ua.kpi.practical_example_8.dbs

import androidx.room.Database
import androidx.room.RoomDatabase
import ua.kpi.practical_example_8.dao.EnergyDao
import ua.kpi.practical_example_8.data.Power
import ua.kpi.practical_example_8.data.Temperature
import ua.kpi.practical_example_8.data.Voltage

// Оголошення бази даних Room для додатку
// Використовується анотація @Database для визначення структури бази даних
@Database(entities = [Power::class, Voltage::class, Temperature::class], version = 1, exportSchema = false)
abstract class MediumAppDatabase : RoomDatabase() {
    // Абстрактний метод, який повертає екземпляр DAO для роботи з енергетичними даними
    // Цей метод буде автоматично реалізований через Room
    abstract fun energyDao(): EnergyDao
}