package com.chskela.weatherappandroid.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.chskela.weatherappandroid.network.WeatherApi
import com.chskela.weatherappandroid.network.data.Hourly
import com.chskela.weatherappandroid.network.data.HourlyForecastData
import com.chskela.weatherappandroid.network.data.WeatherData
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.floor

enum class WeatherApiStatus { LOADING, ERROR, DONE }

class CityViewModel : ViewModel() {
    private val _city = MutableLiveData<String>("Moscow")
    val city: LiveData<String> = _city

    private val _weather = MutableLiveData<WeatherData>()
    val weather: LiveData<WeatherData> = _weather

    private val _hourlyForecastData = MutableLiveData<HourlyForecastData>()
    private val hourlyForecastData: LiveData<HourlyForecastData> = _hourlyForecastData

    var hourly: LiveData<List<Hourly>> = Transformations.map(hourlyForecastData) {
        it.hourly.map { i ->
            i.copy(
                temp = floor(i.temp.toDouble()).toInt().toString(),
                dt = SimpleDateFormat("HH:mm", Locale.getDefault()).format(i.dt.toLong() * 1000)
            )
        }
    }

    val temp: LiveData<String> = Transformations.map(weather) {
        floor(it.main.temp).toInt().toString()
    }

    val timeOfDataCalculation: LiveData<String> = Transformations.map(weather) {
        SimpleDateFormat("HH:mm", Locale.getDefault()).format(it.dt * 1000)
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

                val lat = weather.value?.coord?.lat ?: 0.0
                val lon = weather.value?.coord?.lon ?: 0.0
                _hourlyForecastData.value = WeatherApi.retrofitService.getHourlyForecast(
                    lat, lon
                )

                Log.d("RESULT", "h:___ ${hourly.value.toString()}")

                _status.value = WeatherApiStatus.DONE
                Log.d("RESULT", weather.value.toString())
                Log.d("STATUS", status.value.toString())
            } catch (e: Exception) {
                _status.value = WeatherApiStatus.ERROR
                Log.d("STATUS", status.value.toString())
                Log.d("ERROR", e.toString())
            }
        }
    }
}
