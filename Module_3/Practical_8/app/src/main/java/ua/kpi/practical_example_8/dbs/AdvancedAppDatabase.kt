package ua.kpi.practical_example_8.dbs

import androidx.room.Database
import androidx.room.RoomDatabase
import ua.kpi.practical_example_8.dao.AdvancedEnergyDao
import ua.kpi.practical_example_8.data.SolarPower
import ua.kpi.practical_example_8.data.SolarStation
import ua.kpi.practical_example_8.data.SolarVoltage

@Database(entities = [SolarStation::class, SolarPower::class, SolarVoltage::class], version = 1, exportSchema = false)
abstract class AdvancedAppDatabase : RoomDatabase() {
    abstract fun advancedDao(): AdvancedEnergyDao
}