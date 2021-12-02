package com.chskela.weatherappandroid.network.data

data class Wind(
    val speed: Double, // Wind speed. Unit Default: meter/sec, Metric: meter/sec, Imperial: miles/hour
    val deg: Double, // Wind direction, degrees (meteorological)
    val gust: Double // Wind gust. Unit Default: meter/sec, Metric: meter/sec, Imperial: miles/hour
)