package com.chskela.weatherappandroid.network

import com.chskela.weatherappandroid.network.data.ForecastWeatherData
import com.chskela.weatherappandroid.network.data.WeatherData
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "4ec3bbbfc854473ef842e3e0b5ec5c91"
private const val URI = "https://api.openweathermap.org/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(URI)
    .build()

interface WeatherApiService {
    @GET("data/2.5/weather")
    suspend fun getWeather(
        @Query("q") city: String = "Moscow",
        @Query("units") units: String = "metric",
        @Query("appid") appid: String = API_KEY
    ): WeatherData

    @GET("data/2.5/onecall")
    suspend fun getForecastWeatherData(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String = "metric",
        @Query("exclude") exclude: String = "minutely",
        @Query("appid") appid: String = API_KEY
    ): ForecastWeatherData
}

object WeatherApi {
    val retrofitService: WeatherApiService by lazy {
        retrofit.create(WeatherApiService::class.java)
    }
}
