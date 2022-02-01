package com.chskela.appcompose.utils

import com.chskela.appcompose.R

fun getDrawableId(icon: String) = when (icon) {
    "01d", "01n" -> R.drawable.ic__clear_sky_01
    "02d", "02n" -> R.drawable.ic__few_clouds_02
    "03d", "03n" -> R.drawable.ic__scattered_clouds_03
    "04d", "04n" -> R.drawable.ic__broken_clouds_04
    "09d", "09n" -> R.drawable.ic__shower_rain_09
    "10d", "10n" -> R.drawable.ic__rain_10
    "11d", "11n" -> R.drawable.ic__thunderstorm_11
    "13d", "13n" -> R.drawable.ic__snow_13
    "50d", "50n" -> R.drawable.ic__mist_50
    else -> R.drawable.ic__clear_sky_01
}