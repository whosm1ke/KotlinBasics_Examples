package ua.kpi.practical_example_8.data

import androidx.room.Entity
import androidx.room.PrimaryKey

// Сутність для збереження енергетичного параметра (потужність сонячної електростанції)
@Entity(tableName = "energy_parameters")
data class EnergyParameter(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val power: Double // потужність у кВт
)