package ua.kpi.practical_example_11.basic

data class LoginResponse(
    // Поле для зберігання токена автентифікації, який використовується для доступу до захищених ресурсів
    val token: String,
    
    // Поле для зберігання ролі користувача, наприклад, "ADMIN", "USER" тощо
    val role: String
)