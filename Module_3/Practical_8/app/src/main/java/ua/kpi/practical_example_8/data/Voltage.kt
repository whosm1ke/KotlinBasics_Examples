package ua.kpi.practical_example_8.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Клас, що представляє сутність напруги у базі даних.
 * Використовується для зберігання значень напруги в таблиці 'voltage_table'.
 */
@Entity(tableName = "voltage_table")
data class Voltage(
    /**
     * Унікальний ідентифікатор запису у таблиці.
     * Автоматично генерується при додаванні нового запису.
     */
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    /**
     * Значення напруги (в вольтах), зберігається як число з плаваючою комою.
     */
    val value: Double
)