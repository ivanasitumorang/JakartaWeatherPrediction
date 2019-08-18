package com.azuka.jakartaweatherprediction.entities

import com.squareup.moshi.Json

data class Info(
    @field:Json(name = "dt") val dateTime: Long,
    val main: Main,
    val weather: List<Weather>,
    val wind: Wind,
    @field:Json(name = "dt_txt") val dateText: String
)