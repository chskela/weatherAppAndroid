package com.chskela.weatherappandroid.network.data

data class WeatherData(
    val coord: Coord,
    val weather: List<Weather>,
    val base: String, // Internal parameter
    val main: Main,
    val visibility: Int,
    val wind: Wind,
    val clouds: Clouds,
    val dt: String,
    val sys: Sys,
    val timezone: Int, // Shift in seconds from UTC
    val id: Int, // City ID
    val name: String, //  City name
    val cod: Int // Internal parameter
)

