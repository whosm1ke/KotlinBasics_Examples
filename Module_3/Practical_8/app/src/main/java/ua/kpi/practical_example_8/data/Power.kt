package ua.kpi.practical_example_8.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Клас [Power] представляє сутність енергії в базі даних.
 * Використовується для зберігання значень енергії з урахуванням ідентифікатора та значення.
 */
@Entity(tableName = "power_table")
// Анотація, яка вказує, що цей клас є сутністю для Room бази даних
// Назва таблиці у базі даних буде "power_table"
data class Power(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    // Поле ідентифікатора, яке автоматично генерується при вставці нового запису
    // Значення за замовчуванням - 0
    val value: Double
    // Поле значення енергії типу Double
)