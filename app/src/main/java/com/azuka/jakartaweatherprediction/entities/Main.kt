package com.azuka.jakartaweatherprediction.entities

import com.squareup.moshi.Json

data class Main(
    @field:Json(name = "temp") val temperature: Double,
    val pressure: Double,
    val humidity: Double
)