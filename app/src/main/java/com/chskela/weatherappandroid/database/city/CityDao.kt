package com.chskela.weatherappandroid.database.city

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao {
    @Query("SELECT * FROM city")
    fun getCities(): Flow<List<City>>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = City::class)
    suspend fun insert(city: City)
}

