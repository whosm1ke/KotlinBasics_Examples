package ua.kpi.practical_example_11.medium

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Інтерфейс для роботи з API аутентифікації та захищеними даними.
 * Використовує Retrofit для створення HTTP-запитів.
 */
interface AuthApi {
    /**
     * Виконує логін користувача на сервері.
     * @param request Об'єкт запиту з даними логіну (логін та пароль).
     * @return Відповідь сервера, що містить інформацію про успішний або невдалений логін.
     */
    @POST("login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    /**
     * Реєструє нового користувача на сервері.
     * @param request Об'єкт запиту з даними реєстрації (логін та пароль).
     * @return Відповідь сервера без тіла (Unit), лише статус коду.
     */
    @POST("register")
    suspend fun register(@Body request: LoginRequest): Response<Unit>

    /**
     * Отримує захищені дані, доступні лише автентифікованим користувачам.
     * @return Об'єкт захищених повідомлень.
     */
    @GET("protected")
    suspend fun getProtectedData(): ProtectedMessage

    /**
     * Отримує список сонячних станцій.
     * @return Список об'єктів сонячних станцій.
     */
    @GET("stations")
    suspend fun getStations(): List<SolarStation>
}