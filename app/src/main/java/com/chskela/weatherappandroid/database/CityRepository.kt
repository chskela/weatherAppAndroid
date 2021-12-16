package com.chskela.weatherappandroid.database

import androidx.annotation.WorkerThread
import com.chskela.weatherappandroid.database.city.City
import com.chskela.weatherappandroid.database.city.CityDao
import kotlinx.coroutines.flow.Flow

class CityRepository(private val cityDao: CityDao) {
    val cities: Flow<List<City>> = cityDao.getCities()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(city: City) {
        cityDao.insert(city)
    }
}