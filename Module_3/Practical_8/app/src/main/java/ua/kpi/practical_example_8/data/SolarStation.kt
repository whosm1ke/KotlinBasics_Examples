package ua.kpi.practical_example_8.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Клас, що представляє станцію сонячної енергії у базі даних.
 * Використовується для створення таблиці "solar_station_table" у Room базі даних.
 */
@Entity(tableName = "solar_station_table")
// Описує структуру запису у таблиці
// Кожна станція має унікальний ідентифікатор та назву
// Використовується анотація @Entity для позначення класу як сутності бази даних
// Анотація @PrimaryKey вказує, що поле id є первинним ключем
// autoGenerate = true означає, що ID буде автоматично генеруватись при додаванні нового запису
// Значення за замовчуванням для id = 0 використовується лише для створення об'єктів без ідентифікатора
// Поле name є обов'язковим і містить назву станції
// data class автоматично генерує конструктор, toString, equals та hashCode

data class SolarStation(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String
)