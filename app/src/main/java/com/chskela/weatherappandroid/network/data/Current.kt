package com.chskela.weatherappandroid.network.data

import com.squareup.moshi.Json

// Current weather data API response
data class Current(
    val dt: Long,
    val sunrise: Int,
    val sunset: Int,
    val temp: Double,
    @Json(name = "feels_like") val feelsLike: Double,
    val pressure: Double,
    val humidity: Double,
    @Json(name = "dew_point") val dewPoint: Double,
    val clouds: Int,
    val uvi: Double,
    val visibility: Int,
    @Json(name = "wind_speed") val windSpeed: Double,
    @Json(name = "wind_gust") val windGust: Double,
    @Json(name = "wind_deg") val windDeg: Double,
    val rain: OneHour?,
    val snow: OneHour?,
    val weather: List<Weather>,
)
