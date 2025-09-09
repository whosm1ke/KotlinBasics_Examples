package ua.kpi.practical_example_8.dbs

import androidx.room.Database
import androidx.room.RoomDatabase
import ua.kpi.practical_example_8.dao.EnergyDao
import ua.kpi.practical_example_8.data.Power
import ua.kpi.practical_example_8.data.Temperature
import ua.kpi.practical_example_8.data.Voltage


@Database(entities = [Power::class, Voltage::class, Temperature::class], version = 1, exportSchema = false)
abstract class MediumAppDatabase : RoomDatabase() {
    abstract fun energyDao(): EnergyDao
}