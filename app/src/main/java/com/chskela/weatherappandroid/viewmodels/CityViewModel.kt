package com.chskela.weatherappandroid.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CityViewModel : ViewModel(){
    private val _city = MutableLiveData<String>("Moscow")
    val city: LiveData<String> = _city
}