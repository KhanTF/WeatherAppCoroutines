package com.rage.weatherapp.domain.entity

import java.util.*

data class WeatherEntity(
    val dt: Date,
    val temp: Double,
    val tempMin: Double,
    val tempMax: Double,
    val pressure: Double,
    val humidity: Double,
    val windSpeed: Double,
    val windDegrees: Double,
    val lat: Double,
    val lon: Double
)