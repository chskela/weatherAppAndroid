package com.chskela.weatherappandroid.network.data

import com.squareup.moshi.Json

data class ForecastWeatherData(
    val lon: Double, // City geo location, longitude
    val lat: Double, //  City geo location, latitude
    val timezone: String,
    @Json(name = "timezone_offset") val timezoneOffset: Int,
    val current: Current, // Current weather data API response
//    val minutely: List<Minutely>, // Minute forecast weather data API response
    val hourly: List<Hourly>, // Hourly forecast weather data API response
    val daily: List<Daily>, // Daily forecast weather data API response
)



