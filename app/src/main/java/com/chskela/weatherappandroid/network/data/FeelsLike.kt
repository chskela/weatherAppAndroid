package com.chskela.weatherappandroid.network.data

//This accounts for the human perception of weather
data class FeelsLike(
    val morn: Double, //  Morning temperature.
    val day: Double, // Day temperature.
    val eve: Double, // Evening temperature.
    val night: Double, // Night temperature.
)