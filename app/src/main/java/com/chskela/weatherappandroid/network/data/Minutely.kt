package com.chskela.weatherappandroid.network.data

// Minute forecast weather data API response
data class Minutely(
    val dt: Long,
    val precipitation: Double
)
