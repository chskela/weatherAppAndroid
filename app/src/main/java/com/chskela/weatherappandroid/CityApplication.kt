package com.chskela.weatherappandroid

import android.app.Application
import com.chskela.weatherappandroid.database.AppDatabase
import com.chskela.weatherappandroid.database.CityRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class CityApplication : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())

    private val database by lazy { AppDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { CityRepository(database.cityDao()) }
}