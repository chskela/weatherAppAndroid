package com.chskela.weatherappandroid.database.city

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class City(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @NonNull @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "state") val state: String,
    @ColumnInfo(name = "country") val country: String,
    @NonNull @ColumnInfo(name = "lon") val lon: Double,
    @NonNull @ColumnInfo(name = "lat") val lat: Double,
)
