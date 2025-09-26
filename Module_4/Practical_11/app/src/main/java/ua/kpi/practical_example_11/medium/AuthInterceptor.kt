package ua.kpi.practical_example_11.medium

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val authDataStore: AuthDataStore) : Interceptor {
    // Метод intercept викликається для кожного HTTP-запиту, щоб додати авторизаційний заголовок
    override fun intercept(chain: Interceptor.Chain): Response {
        // Отримуємо оригінальний запит з ланцюжка
        val original = chain.request()
        
        // Асинхронно отримуємо токен з data store, використовуючи runBlocking для блокування потоку
        val token = runBlocking { authDataStore.tokenFlow.first() }
        
        // Перевіряємо, чи токен існує і не є порожнім
        val request = if (!token.isNullOrEmpty()) {
            // Якщо токен існує, додаємо заголовок Authorization з Bearer токеном
            original.newBuilder()
                .header("Authorization", "Bearer $token")
                .build()
        } else {
            // Якщо токена немає, залишаємо оригінальний запит без змін
            original
        }
        
        // Виконуємо запит із модифікованим або оригінальним запитом
        return chain.proceed(request)
    }
}