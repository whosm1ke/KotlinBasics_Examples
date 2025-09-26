package ua.kpi.practical_example_8.data

import androidx.room.Entity
import androidx.room.PrimaryKey

// Це сутність (Entity) для зберігання даних про температуру в базі даних
// Використовується разом із Room бібліотекою для опису таблиці в БД
@Entity(tableName = "temperature_table")
// Дата-клас, який представляє одну запис температури
// Він містить два поля: id та value
// id - унікальний ідентифікатор запису, автоматично генерується
// value - значення температури у вигляді дробового числа (Double)
data class Temperature(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,  // Унікальний ідентифікатор запису, автоматично збільшується при додаванні нового запису
    val value: Double  // Значення температури (в градусах Цельсія або інших одиницях)
)