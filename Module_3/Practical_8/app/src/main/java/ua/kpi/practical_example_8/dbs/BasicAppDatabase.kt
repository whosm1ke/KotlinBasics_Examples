package ua.kpi.practical_example_8.dbs

import androidx.room.Database
import androidx.room.RoomDatabase
import ua.kpi.practical_example_8.dao.EnergyParameterDao
import ua.kpi.practical_example_8.data.EnergyParameter


@Database(entities = [EnergyParameter::class], version = 1, exportSchema = false)
abstract class BasicAppDatabase : RoomDatabase() {
    abstract fun energyParameterDao(): EnergyParameterDao
}