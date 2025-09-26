package ua.kpi.practical_example_8.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Сущность для хранения данных о солнечной мощности.
 * Используется в базе данных Room для представления записей о показаниях солнечной энергии.
 */
@Entity(
    tableName = "solar_power_table",  // Название таблицы в базе данных
    foreignKeys = [ForeignKey(
        entity = SolarStation::class,           // Связанная сущность (станция)
        parentColumns = ["id"],                 // Колонка в родительской таблице
        childColumns = ["stationId"],           // Колонка в текущей таблице
        onDelete = ForeignKey.Companion.CASCADE   // При удалении станции, удалять все связанные записи
    )],
    indices = [Index("stationId")]  // Создание индекса для поля stationId для ускорения запросов
)
data class SolarPower(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,  // Уникальный идентификатор записи (генерируется автоматически)
    val value: Double,                                 // Значение солнечной мощности
    val stationId: Int                                 // Идентификатор станции, к которой относится эта запись
)