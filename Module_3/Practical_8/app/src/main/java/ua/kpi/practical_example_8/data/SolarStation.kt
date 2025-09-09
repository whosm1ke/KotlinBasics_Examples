package ua.kpi.practical_example_8.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "solar_station_table")
data class SolarStation(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String
)

