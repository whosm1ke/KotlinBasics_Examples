package ua.kpi.practical_example_27.components

import androidx.compose.animation.animateContentSize // Анімація зміни розміру компонента при зміні вмісту
import androidx.compose.foundation.layout.Arrangement // Компонент для розподілу вмісту по горизонталі
import androidx.compose.foundation.layout.Row // Компонент для горизонтального розміщення елементів
import androidx.compose.foundation.layout.fillMaxWidth // Модифікатор для заповнення максимальної ширини
import androidx.compose.foundation.layout.padding // Модифікатор для додавання відступів
import androidx.compose.foundation.shape.RoundedCornerShape // Форма картки з закругленими кутами
import androidx.compose.material3.Card // Компонент картки Material Design
import androidx.compose.material3.Text // Компонент тексту
import androidx.compose.runtime.Composable // Анотація для позначення Composable функцій
import androidx.compose.ui.Modifier // Клас для модифікації компонентів
import androidx.compose.ui.unit.dp // Одиниця виміру dp (density-independent pixels)
import ua.kpi.practical_example_27.models.SolarForecast // Модель даних для прогнозу сонячної енергії

@Composable
fun ForecastItem(forecast: SolarForecast) {
    // Створення картки з прогнозом
    Card(modifier = Modifier
        .fillMaxWidth() // Заповнює всю доступну ширину
        .padding(vertical = 4.dp) // Відступи по вертикалі
        .animateContentSize(), // Активує анімацію зміни розміру при зміні вмісту
        shape = RoundedCornerShape(8.dp) // Закруглені кути картки
    ) {
        // Горизонтальний рядок з даними прогнозу
        Row(modifier = Modifier
            .fillMaxWidth() // Заповнює всю доступну ширину
            .padding(16.dp), // Внутрішні відступи
            horizontalArrangement = Arrangement.SpaceBetween // Розміщує елементи з максимальним відстанню між ними
        ) {
            // Відображення часу прогнозу
            Text(forecast.time)
            // Відображення потужності у кіловаттах
            Text("${forecast.powerKW} kW")
        }
    }
}