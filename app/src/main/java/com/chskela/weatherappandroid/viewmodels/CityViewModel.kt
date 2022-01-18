package com.chskela.weatherappandroid.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.chskela.weatherappandroid.R
import com.chskela.weatherappandroid.database.CityRepository
import com.chskela.weatherappandroid.database.city.City
import com.chskela.weatherappandroid.network.WeatherApi
import com.chskela.weatherappandroid.network.data.Coord
import com.chskela.weatherappandroid.network.data.ForecastWeatherData
import com.chskela.weatherappandroid.network.data.WeatherData
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.floor

class CityViewModel(private val repository: CityRepository) : ViewModel() {

    private val _city = MutableLiveData<String>("Moscow")
    val city: LiveData<String> = _city

    private val _location = MutableLiveData<Coord>()
    val location: LiveData<Coord> = _location

    fun setLocation(coord: Coord) {
        Log.w("RESULT", _location.value.toString())
        _location.value = coord
        Log.w("RESULT", _location.value.toString())
    }

    val cities: LiveData<List<City>> = repository.cities.asLiveData()

    private val _weather = MutableLiveData<WeatherData>()
    val weatherData: LiveData<WeatherData> = _weather

    private val _forecastWeatherData = MutableLiveData<ForecastWeatherData>()
    private val forecastWeatherData: LiveData<ForecastWeatherData> = _forecastWeatherData

    val current: LiveData<UIData> = Transformations.map(weatherData) {
        UIData(
            temp = floor(it.main.temp).toInt().toString(),
            dt = SimpleDateFormat("HH:mm", Locale.getDefault()).format(it.dt * 1000),
            icon = getDrawableId(it.weather[0].icon),
            description = it.weather[0].description
        )
    }

    val hourly: LiveData<List<UIData>> = Transformations.map(forecastWeatherData) {
        it.hourly
            .drop(1)
            .filterIndexed { index, _ -> index % 3 == 0 }
//            .subList(0, 5)
            .map { i ->
                UIData(
                    temp = floor(i.temp).toInt().toString(),
                    dt = SimpleDateFormat(
                        "HH:mm",
                        Locale.getDefault()
                    ).format(i.dt * 1000),
                    icon = getDrawableId(i.weather[0].icon),
                    description = i.weather[0].description
                )
            }
    }

    val daily: LiveData<List<UIData>> = Transformations.map(forecastWeatherData) {
        it.daily
            .drop(1)
            .map { i ->
                UIData(
                    temp = floor(i.temp.day).toInt().toString(),
                    dt = SimpleDateFormat(
                        "dd.MM",
                        Locale.getDefault()
                    ).format(i.dt * 1000),
                    icon = getDrawableId(i.weather[0].icon),
                    description = i.weather[0].description
                )
            }
    }

    private val _status = MutableLiveData<WeatherApiStatus>(WeatherApiStatus.LOADING)
    val status: LiveData<WeatherApiStatus> = _status

    init {
//        city.value?.let { getWeatherOfCity(it) }

    }

    fun getWeatherByLocation() {
        Log.w("RESULT", "start getWeatherByLocation")
        val coord = location.value
        Log.w("RESULT", location.value.toString())
        if (coord != null) {
            Log.w("RESULT", "coord != null")
            viewModelScope.launch {
//                _status.value = WeatherApiStatus.LOADING
                Log.d("RESULT", _status.value.toString())
                try {
                    Log.d("RESULT", coord.toString())
                    Log.d("RESULT", "start")
//                    _forecastWeatherData.value = WeatherApi.retrofitService.getForecastWeatherData(
//                        122.0839, 37.4234
//                    )
                    _forecastWeatherData.value = WeatherApi.retrofitService.getForecastWeatherData(
                        coord.lat, coord.lon
                    )
                    Log.d("RESULT", "end")
                    Log.d("RESULT", _forecastWeatherData.value.toString())
                    _status.value = WeatherApiStatus.DONE
                    Log.d("RESULT",  _status.value.toString())
                } catch (e: Exception) {
                    e.message?.let { Log.d("RESULT", it) }
                    _status.value = WeatherApiStatus.ERROR
                }
            }
        }

    }

    private fun getDrawableId(icon: String) = when (icon) {
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

    fun insert(city: City) = viewModelScope.launch {
        repository.insert(city)
    }

    class CityViewModelFactory(private val repository: CityRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            Log.w("RESULT", "Create  CityViewModel")
            if (modelClass.isAssignableFrom(CityViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CityViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }
}
