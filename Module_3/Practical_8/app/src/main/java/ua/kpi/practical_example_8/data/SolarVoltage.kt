package ua.kpi.practical_example_8.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Сутність, що представляє напругу сонячної станції.
 * Використовується для зберігання даних про вимірювання напруги відповідно до станції.
 */
@Entity(
    tableName = "solar_voltage_table", // Назва таблиці в базі даних
    foreignKeys = [ForeignKey(
        entity = SolarStation::class, // Сутність, на яку посилається зовнішній ключ
        parentColumns = ["id"], // Поле в таблиці батьківської сутності
        childColumns = ["stationId"], // Поле в поточній таблиці, що посилається на батьківську
        onDelete = ForeignKey.Companion.CASCADE // Дія при видаленні батьківської сутності
    )],
    indices = [Index("stationId")] // Створення індексу для поля stationId для покращення швидкості запитів
)
data class SolarVoltage(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, // Унікальний ідентифікатор запису, генерується автоматично
    val value: Double, // Значення напруги
    val stationId: Int // Ідентифікатор станції, до якої належить це вимірювання
)