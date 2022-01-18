package com.chskela.weatherappandroid.network.data

import java.io.Serializable

data class Coord(
    val lon: Double, // City geo location, longitude
    val lat: Double //  City geo location, latitude
) : Serializable