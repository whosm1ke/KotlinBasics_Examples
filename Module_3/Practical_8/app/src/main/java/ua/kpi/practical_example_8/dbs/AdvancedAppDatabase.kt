package ua.kpi.practical_example_8.dbs

import androidx.room.Database
import androidx.room.RoomDatabase
import ua.kpi.practical_example_8.dao.AdvancedEnergyDao
import ua.kpi.practical_example_8.data.SolarPower
import ua.kpi.practical_example_8.data.SolarStation
import ua.kpi.practical_example_8.data.SolarVoltage

/**
 * Клас AdvancedAppDatabase є головним класом бази даних Room для застосунку.
 * Він наслідує RoomDatabase і визначає структуру бази даних, включаючи сутності та DAO.
 */
@Database(
    entities = [SolarStation::class, SolarPower::class, SolarVoltage::class], // Визначення сутностей, які будуть зберігатися в базі даних
    version = 1, // Версія бази даних, використовується для міграцій
    exportSchema = false // Вказує, чи потрібно експортувати схему бази даних у файл (false — не експортує)
)
abstract class AdvancedAppDatabase : RoomDatabase() {
    /**
     * Абстрактний метод, який повертає екземпляр AdvancedEnergyDao.
     * Це дозволяє отримати доступ до методів для роботи з даними через DAO.
     */
    abstract fun advancedDao(): AdvancedEnergyDao
}