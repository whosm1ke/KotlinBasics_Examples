package ua.kpi.practical_example_10.basic

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// ----------------------------
// Retrofit instance
// ----------------------------
object RetrofitClient {
    private const val BASE_URL = "http://10.0.2.2:5156/" // localhost для емулятора

    val api: SolarApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SolarApiService::class.java)
    }
}