package com.chskela.weatherappandroid.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.chskela.weatherappandroid.R
import com.chskela.weatherappandroid.network.WeatherApi
import com.chskela.weatherappandroid.network.data.ForecastWeatherData
import com.chskela.weatherappandroid.network.data.WeatherData
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.floor

class CityViewModel : ViewModel() {
    private val _city = MutableLiveData<String>("Moscow")
    val city: LiveData<String> = _city

    private val _weather = MutableLiveData<WeatherData>()
    val weatherData: LiveData<WeatherData> = _weather

    private val _forecastWeatherData = MutableLiveData<ForecastWeatherData>()
    private val forecastWeatherData: LiveData<ForecastWeatherData> = _forecastWeatherData

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

    val current: LiveData<UIData> = Transformations.map(weatherData) {
        UIData(
            temp =  floor(it.main.temp).toInt().toString(),
            dt = SimpleDateFormat("HH:mm", Locale.getDefault()).format(it.dt * 1000),
            icon = getDrawableId(it.weather[0].icon),
            description = it.weather[0].description
        )
    }

    val temp: LiveData<String> = Transformations.map(weatherData) {
        floor(it.main.temp).toInt().toString()
    }

    val timeOfDataCalculation: LiveData<String> = Transformations.map(weatherData) {
        SimpleDateFormat("HH:mm", Locale.getDefault()).format(it.dt.toLong() * 1000)
    }

    val icon: LiveData<Int> = Transformations.map(weatherData) {
        getDrawableId(it.weather[0].icon)
    }

    val description: LiveData<String> = Transformations.map(weatherData) {
        it.weather.map { i ->
            i.description
        }[0]
    }

    private val _status = MutableLiveData<WeatherApiStatus>()
    val status: LiveData<WeatherApiStatus> = _status

    init {
        getWeatherOfCity("Moscow")
    }

    private fun getWeatherOfCity(city: String) {
        viewModelScope.launch {
            _status.value = WeatherApiStatus.LOADING
            Log.d("STATUS", status.value.toString())
            try {
                _weather.value = WeatherApi.retrofitService.getWeather("Sochi")

                val lat = weatherData.value?.coord?.lat ?: 0.0
                val lon = weatherData.value?.coord?.lon ?: 0.0
                _forecastWeatherData.value = WeatherApi.retrofitService.getForecastWeatherData(
                    lat, lon
                )

                Log.d("RESULT", "h:___ ${daily.value.toString()}")

                _status.value = WeatherApiStatus.DONE
                Log.d("RESULT", weatherData.value.toString())
                Log.d("STATUS", status.value.toString())
            } catch (e: Exception) {
                _status.value = WeatherApiStatus.ERROR
                Log.d("STATUS", status.value.toString())
                Log.d("ERROR", e.toString())
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
}
