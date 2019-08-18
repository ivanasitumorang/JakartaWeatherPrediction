package com.azuka.jakartaweatherprediction.network

import com.azuka.jakartaweatherprediction.entities.Info
import com.azuka.jakartaweatherprediction.entities.WeatherResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "http://api.openweathermap.org/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val okHttpClient = OkHttpClient().newBuilder().build()

private val retrofit = Retrofit.Builder()
    .client(okHttpClient)
    .addConverterFactory(MoshiConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface WeatherApiService {
    @GET("data/2.5/weather")
    fun getWeatherByName(
        @Query("appid") apiKey: String,
        @Query("q") locationName: String
    ) : Deferred<WeatherResponse>

    @GET("data/2.5/weather")
    fun getWeatherById(
        @Query("appid") apiKey: String,
        @Query("id") cityId: String
    ) : Deferred<WeatherResponse>

    @GET("data/2.5/forecast")
    fun getWeatherByCoordinate(
        @Query("appid") apiKey: String,
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") unit: String = "metric"
    ) : Deferred<WeatherResponse>
}

object WeatherApi {
    const val API_KEY = "a5c9145860bd859c4ab64217cd90d840"
    val retrofitService: WeatherApiService by lazy {
        retrofit.create(WeatherApiService::class.java)
    }
}
