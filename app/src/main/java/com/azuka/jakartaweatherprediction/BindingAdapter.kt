package com.azuka.jakartaweatherprediction

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

//http://openweathermap.org/img/wn/02d@2x.png

@BindingAdapter("timeFormat")
fun TextView.setTime(date: String?){
    date?.let {
        text = date.toDate()!!.formatToTime()
    }
}

@BindingAdapter("windSpeedFormat")
fun TextView.setWindSpeed(windSpeed: Double?){
    windSpeed?.let {
        text = String.format(context.getString(R.string.windspeed_format), windSpeed)
    }
}

@BindingAdapter("temperatureFormat")
fun TextView.setTemperature(temp: Double?){
    temp?.let {
        text = String.format(context.getString(R.string.degree_celcius_format), temp)
    }
}

@BindingAdapter("humidityFormat")
fun TextView.setHumidity(humadity: Double?){
    humadity?.let {
        text = String.format(context.getString(R.string.humidity_format), humadity)
    }
}

@BindingAdapter("weatherIcon")
fun ImageView.setWeatherIcon(url: String?){
    val imageUrl = "http://openweathermap.org/img/wn/${url}@2x.png"
    Picasso.get()
        .load(imageUrl)
        .placeholder(R.drawable.loading_animation)
        .error(R.drawable.ic_broken_image)
        .into(this)
}
