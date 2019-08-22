package com.azuka.jakartaweatherprediction.entities

import com.squareup.moshi.Json

data class ForecastResponse(
    val cod: String,
    val message: Double,
    @field:Json(name = "cnt") val count: Int,
    val list: List<Info>,
    val city: City
)