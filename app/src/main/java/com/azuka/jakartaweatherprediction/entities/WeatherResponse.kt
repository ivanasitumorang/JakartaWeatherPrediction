package com.azuka.jakartaweatherprediction.entities

data class WeatherResponse(
    val weather: List<Weather>,
    val main: Main,
    val wind: Wind,
    val sys: Sys,
    val name: String,
    val id: Long
)