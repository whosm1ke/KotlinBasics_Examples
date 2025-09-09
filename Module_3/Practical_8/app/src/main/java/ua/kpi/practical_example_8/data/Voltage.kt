package ua.kpi.practical_example_8.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "voltage_table")
data class Voltage(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val value: Double
)
