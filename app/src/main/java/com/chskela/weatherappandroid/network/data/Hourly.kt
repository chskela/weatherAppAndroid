package com.chskela.weatherappandroid.network.data

import com.squareup.moshi.Json

data class Hourly(
    val dt: String,
    val temp: String,
    val weather: List<Weather>,
//    @Json(name = "feels_like") val feelsLike: Double,
//    val pressure: Double,
//    val humidity: Double,
//    @Json(name = "dew_point") val dewPoint: Double,
//    val clouds: Int,
//    val uvi: Double,
//    val visibility: Int,
//    @Json(name = "wind_speed") val windSpeed: Double,
//    @Json(name = "wind_gust") val windGust: Double,
//    @Json(name = "wind_deg") val windDeg: Double,
//    val pop: Double,
//    val rain: OneHour?,
//    val snow: OneHour?,

)
