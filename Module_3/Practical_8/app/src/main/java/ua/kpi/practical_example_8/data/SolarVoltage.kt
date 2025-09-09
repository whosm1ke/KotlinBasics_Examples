package ua.kpi.practical_example_8.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "solar_voltage_table",
    foreignKeys = [ForeignKey(
        entity = SolarStation::class,
        parentColumns = ["id"],
        childColumns = ["stationId"],
        onDelete = ForeignKey.Companion.CASCADE
    )],
    indices = [Index("stationId")]
)
data class SolarVoltage(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val value: Double,
    val stationId: Int
)