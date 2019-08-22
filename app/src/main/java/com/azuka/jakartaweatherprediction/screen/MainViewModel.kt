package com.azuka.jakartaweatherprediction.screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.azuka.jakartaweatherprediction.entities.Info
import com.azuka.jakartaweatherprediction.entities.Jakarta
import com.azuka.jakartaweatherprediction.entities.ForecastResponse
import com.azuka.jakartaweatherprediction.entities.WeatherResponse
import com.azuka.jakartaweatherprediction.formatToDate
import com.azuka.jakartaweatherprediction.network.WeatherApi
import com.azuka.jakartaweatherprediction.toDate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.*

class MainViewModel : ViewModel(){
    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _forecast = MutableLiveData<ForecastResponse>()
    val forecast: LiveData<ForecastResponse>
        get() = _forecast

    private val _infoWeatherList= MutableLiveData<List<Info>>()
    val infoWeatherList: LiveData<List<Info>>
        get() = _infoWeatherList

    private val _todayDate = MutableLiveData<Long>()
    val todayDate: LiveData<Long>
        get() = _todayDate

    val todayString = Transformations.map(todayDate) { today ->
        val dt = Date(today)
        dt.formatToDate(timeZone = TimeZone.getDefault())
    }

    val hourlyWeatherToday: LiveData<List<Info>>
        get() = Transformations.switchMap(infoWeatherList) {
            val _hourlyWeatherToday = MutableLiveData<List<Info>>()
            val filteredList = it.filter { info ->
                info.dateText.toDate()!!.formatToDate() == todayString.value
            }.toList()
            _hourlyWeatherToday.value = filteredList
            _hourlyWeatherToday
        }

    private val _currentWeather = MutableLiveData<WeatherResponse>()
    val currentWeather: LiveData<WeatherResponse> = _currentWeather

    init {
        _todayDate.value = System.currentTimeMillis()
        getCurrentWeather()
        getForecast()
    }

    fun getForecast() {
        coroutineScope.launch {
            val getForecastDeferred = WeatherApi.retrofitService
                .getForecastByCoordinate(WeatherApi.API_KEY, Jakarta().coordinate.lat, Jakarta().coordinate.lon)

            try {
                val result = getForecastDeferred.await()
                _forecast.value = result
                _infoWeatherList.value = forecast.value!!.list
                Log.i("ViewModel", "getForecast() count ${result.count}")
            } catch (e: Exception){
                Log.i("ViewModel", "getForecast() ${e.message}")
            }
        }
    }

    fun getCurrentWeather() {
        coroutineScope.launch {
            val getWeatherDeferred = WeatherApi.retrofitService
                .getCurrentWeatherByCoordinate(WeatherApi.API_KEY, Jakarta().coordinate.lat, Jakarta().coordinate.lon)
            try {
                val result = getWeatherDeferred.await()
                _currentWeather.value = result
            } catch (e: Exception){
                Log.i("ViewModel", "getCurrentWeather() ${e.message}")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}