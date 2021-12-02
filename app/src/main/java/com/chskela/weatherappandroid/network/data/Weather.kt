package com.chskela.weatherappandroid.network.data

data class Weather(
    val id: Int, // Weather condition id
    val main: String, // Group of weather parameters (Rain, Snow, Extreme etc.)
    val description: String, // Weather condition within the group. Get the output in your language.
    val icon: String // Weather icon id
    )