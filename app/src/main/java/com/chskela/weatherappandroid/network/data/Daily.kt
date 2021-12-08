package com.chskela.weatherappandroid.network.data

import com.squareup.moshi.Json

//daily Daily forecast weather data API response
data class Daily(
    val dt: Long, // Time of the forecasted data, Unix, UTC
//    val sunrise: Int, // Sunrise time, Unix, UTC
//    val sunset: Int, //  Sunset time, Unix, UTC
//    val moonrise: Int, // The time of when the moon rises for this day, Unix, UTC
//    val moonset: Int, // The time of when the moon sets for this day, Unix, UTC
    /*
    Moon phase. 0 and 1 are 'new moon', 0.25 is 'first quarter moon',
    0.5 is 'full moon' and 0.75 is 'last quarter moon'.
    The periods in between are called 'waxing crescent',
    'waxing gibous', 'waning gibous', and 'waning crescent', respectively.
     */
//    @Json(name = "moon_phase") val moonPhase: Double,
    val temp: Temperature, // Units – default: kelvin, metric: Celsius, imperial: Fahrenheit
//    @Json(name = "feels_like") val feelsLike: FeelsLike, //This accounts for the human perception of weather
//    val pressure: Double, // Atmospheric pressure on the sea level, hPa
//    val humidity: Double, // Atmospheric pressure on the sea level, hPa
    /*
    Atmospheric temperature (varying according to pressure and humidity)
    below which water droplets begin to condense and dew can form.
    Units – default: kelvin, metric: Celsius, imperial: Fahrenheit.
     */
//    @Json(name = "dew_point") val dewPoint: Double,
    /*
    Wind speed.
    Units – default: metre/sec, metric: metre/sec, imperial: miles/hour.
     */
//    @Json(name = "wind_speed") val windSpeed: Double,
    /*
    Wind gust.
    Units – default: metre/sec, metric: metre/sec, imperial: miles/hour.
     */
//    @Json(name = "wind_gust") val windGust: Double,
//    @Json(name = "wind_deg") val windDeg: Double, // Wind direction, degrees (meteorological)
//    val clouds: Int, // Cloudiness, %
//    val uvi: Double, // The maximum value of UV index for the day
//    val pop: Double, // Probability of precipitation
//    val rain: Double?, // Precipitation volume, mm
//    val snow: Double?, // Snow volume, mm
    val weather: List<Weather>,
//    val alert: Alert? // National weather alerts data from major national weather warning systems
)