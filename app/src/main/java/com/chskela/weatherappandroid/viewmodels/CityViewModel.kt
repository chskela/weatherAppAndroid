package com.chskela.weatherappandroid.viewmodels

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

    var hourly: LiveData<List<UIData>> = Transformations.map(forecastWeatherData) {
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

    var daily: LiveData<List<UIData>> = Transformations.map(forecastWeatherData) {
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

    private val _status = MutableLiveData<WeatherApiStatus>()
    val status: LiveData<WeatherApiStatus> = _status

    init {
        city.value?.let { getWeatherOfCity(it) }
    }

    private fun getWeatherOfCity(city: String) {
        viewModelScope.launch {
            _status.value = WeatherApiStatus.LOADING

            try {
                _weather.value = WeatherApi.retrofitService.getWeatherByCity(city)

                val lat = weatherData.value?.coord?.lat ?: 0.0
                val lon = weatherData.value?.coord?.lon ?: 0.0

                _forecastWeatherData.value = WeatherApi.retrofitService.getForecastWeatherData(
                    lat, lon
                )
                _status.value = WeatherApiStatus.DONE

            } catch (e: Exception) {
                _status.value = WeatherApiStatus.ERROR
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
            if (modelClass.isAssignableFrom(CityViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CityViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }
}
