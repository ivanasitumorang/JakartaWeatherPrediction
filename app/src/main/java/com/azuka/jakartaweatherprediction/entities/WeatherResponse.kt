package com.azuka.jakartaweatherprediction.entities

import com.squareup.moshi.Json

data class WeatherResponse(
    val weather: List<Weather>,
    val main: Main,
    val wind: Wind,
    val sys: Sys,
    val name: String,
    val id: Long,
    @field:Json(name = "cod") val code: Int
)