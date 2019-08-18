package com.azuka.jakartaweatherprediction.screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.azuka.jakartaweatherprediction.entities.Info
import com.azuka.jakartaweatherprediction.entities.Jakarta
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
    private val _cityInfo = MutableLiveData<WeatherResponse>()
    val cityInfo: LiveData<WeatherResponse>
        get() = _cityInfo

    private val _infoWeatherList= MutableLiveData<List<Info>>()
    val infoWeatherList: LiveData<List<Info>>
        get() = _infoWeatherList

    private val _todayDate = MutableLiveData<Long>()
    val todayDate: LiveData<Long>
        get() = _todayDate

    private var timeZone: TimeZone = TimeZone.getDefault()

    val todayString = Transformations.map(todayDate, { today ->
        val dt = Date(today)
        dt.formatToDate(timeZone = TimeZone.getDefault())
    })

    val hourlyWeatherToday: LiveData<List<Info>>
        get() = Transformations.switchMap(infoWeatherList, {
            val _hourlyWeatherToday = MutableLiveData<List<Info>>()
            val filteredList = it.filter { info ->
                info.dateText.toDate()!!.formatToDate().equals(todayString.value)
            }.toList()
            _hourlyWeatherToday.value = filteredList
            _hourlyWeatherToday
        })

    init {
        _todayDate.value = System.currentTimeMillis()
        getWeather()
    }


    fun getWeather() {
        coroutineScope.launch {
            val getWeatherDeferred = WeatherApi.retrofitService
                .getWeatherByCoordinate(WeatherApi.API_KEY, Jakarta().coordinate.lat, Jakarta().coordinate.lon)

            try {
                val result = getWeatherDeferred.await()
                _cityInfo.value = result
                _infoWeatherList.value = cityInfo.value!!.list
                Log.i("ViewModel", "getWeather() count ${result.count}")
            } catch (e: Exception){
                Log.i("ViewModel", "getWeather() ${e.message}")
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}