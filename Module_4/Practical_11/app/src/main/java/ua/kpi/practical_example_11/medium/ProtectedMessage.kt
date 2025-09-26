package ua.kpi.practical_example_11.medium

// Клас ProtectedMessage використовується для представлення захищеного повідомлення,
// яке містить лише одне поле - текст повідомлення.
data class ProtectedMessage(
    // Публічне властивість, що зберігає текст захищеного повідомлення
    val message: String
)