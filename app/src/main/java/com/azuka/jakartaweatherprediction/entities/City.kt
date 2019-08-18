package com.azuka.jakartaweatherprediction.entities

import com.squareup.moshi.Json

data class City(
    val id: Long,
    val name: String,
    @Json(name = "coord") val coordinate: Coordinate,
    val country: String,
    val timezone: Long,
    val sunrise: Long,
    val sunset: Long
)

data class Coordinate(
    val lat: Double,
    val lon: Double
)