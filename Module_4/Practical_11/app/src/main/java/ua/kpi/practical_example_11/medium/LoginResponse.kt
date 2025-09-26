package ua.kpi.practical_example_11.medium

data class LoginResponse(
    // Поле, що містить токен автентифікації для доступу до захищених ресурсів
    val token: String,
    // Поле, що вказує роль користувача (наприклад, "USER", "ADMIN")
    val role: String
)