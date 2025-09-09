package ua.kpi.practical_example_8.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "temperature_table")
data class Temperature(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val value: Double
)