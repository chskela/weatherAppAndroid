package com.chskela.weatherappandroid.network.data

import com.squareup.moshi.Json

//National weather alerts data from major national weather warning systems
data class Alert(
    @Json(name = "sender_name") val senderName: String, //Name of the alert source.
    val event: String, // Alert event name
    val start: Int, //Date and time of the start of the alert, Unix, UTC
    val end: Int, //Date and time of the end of the alert, Unix, UTC
    val description: String, //alerts.description Description of the alert
    val tags: List<String> //Type of severe weather
)