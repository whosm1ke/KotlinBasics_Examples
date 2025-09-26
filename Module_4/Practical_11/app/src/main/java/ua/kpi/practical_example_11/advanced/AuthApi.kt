package ua.kpi.practical_example_11.advanced

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import ua.kpi.practical_example_11.medium.LoginRequest
import ua.kpi.practical_example_11.medium.LoginResponse
import ua.kpi.practical_example_11.medium.SolarStation

/**
 * Інтерфейс для взаємодії з API автентифікації та захисту даних.
 * Використовує Retrofit для створення HTTP-запитів.
 */
interface AuthApi {
    /**
     * Виконує логін користувача на сервері.
     * Використовує метод POST з тілом запиту типу LoginRequest.
     * Повертає об'єкт LoginResponse з даними про успішну аутентифікацію.
     *
     * @param request Об'єкт запиту для логіну, що містить дані користувача
     * @return Результат логіну у вигляді LoginResponse
     */
    @POST("login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    /**
     * Реєструє нового користувача на сервері.
     * Використовує метод POST з тілом запиту типу LoginRequest.
     * Повертає об'єкт Response<Unit>, оскільки реєстрація не повертає конкретних даних.
     *
     * @param request Об'єкт запиту для реєстрації, що містить дані користувача
     * @return Відповідь сервера на запит реєстрації
     */
    @POST("register")
    suspend fun register(@Body request: LoginRequest): Response<Unit>

    /**
     * Отримує захищені дані, доступні лише авторизованим користувачам.
     * Використовує метод GET без параметрів.
     * Повертає об'єкт ProtectedMessage з захищеною інформацією.
     *
     * @return Об'єкт захищених даних
     */
    @GET("protected")
    suspend fun getProtectedData(): ProtectedMessage

    /**
     * Отримує список сонячних станцій.
     * Використовує метод GET без параметрів.
     * Повертає список об'єктів SolarStation.
     *
     * @return Список сонячних станцій
     */
    @GET("stations")
    suspend fun getStations(): List<SolarStation>

    /**
     * Отримує список сонячних станцій, доступний лише для адміністраторів.
     * Використовує метод GET без параметрів.
     * Повертає список об'єктів SolarStation.
     *
     * @return Список сонячних станцій для адміністраторів
     */
    @GET("admin-only")
    suspend fun getAdminOnly(): List<SolarStation>
}