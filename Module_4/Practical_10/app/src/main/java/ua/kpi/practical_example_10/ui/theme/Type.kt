package ua.kpi.practical_example_10.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Оголошення об'єкта Typography з використанням Material Design типографії
// Цей об'єкт містить стилі тексту, які можна використовувати у Compose-додатку
val Typography = Typography(
    // Визначення стилю для великого тексту (наприклад, основного тексту на сторінці)
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,  // Використовуємо стандартну системну шрифтову родину
        fontWeight = FontWeight.Normal,   // Нормальна товщина шрифту
        fontSize = 16.sp,                 // Розмір шрифту 16 пікселів (специфікація для Compose)
        lineHeight = 24.sp,               // Висота рядка (відстань між рядками тексту)
        letterSpacing = 0.5.sp            // Пропуск між символами
    )
    /* Інші стилі за замовчуванням, які можна перевизначити:
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)