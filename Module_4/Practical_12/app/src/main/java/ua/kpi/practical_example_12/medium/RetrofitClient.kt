package ua.kpi.practical_example_12.medium

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// === Retrofit-клієнт ===
object RetrofitClient {
    private const val BASE_URL = "http://10.0.2.2:5156/"

    val api: ForecastApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ForecastApiService::class.java)
    }
}