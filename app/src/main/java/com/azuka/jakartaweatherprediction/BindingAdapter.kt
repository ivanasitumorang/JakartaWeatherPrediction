package com.azuka.jakartaweatherprediction

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.azuka.jakartaweatherprediction.entities.Info

//http://openweathermap.org/img/wn/02d@2x.png

@BindingAdapter("timeFormat")
fun TextView.setTime(infoWeather: Info?){
    infoWeather?.let {
        text = infoWeather.dateText.toDate()!!.formatToTime()
    }
}

@BindingAdapter("windSpeedFormat")
fun TextView.setWindSpeed(infoWeather: Info?){
    infoWeather?.let {
        text = String.format(context.getString(R.string.windspeed_format), infoWeather.wind.speed)
    }
}

@BindingAdapter("temperatureFormat")
fun TextView.setTemperature(infoWeather: Info?){
    infoWeather?.let {
        text = String.format(context.getString(R.string.degree_celcius_format), infoWeather.main.temperature)
    }
}