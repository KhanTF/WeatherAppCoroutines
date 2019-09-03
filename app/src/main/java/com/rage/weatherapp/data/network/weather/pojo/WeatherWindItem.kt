package com.rage.weatherapp.data.network.weather.pojo

import com.google.gson.annotations.SerializedName

data class WeatherWindItem(
    @field:SerializedName("speed")
    val speed: Double?,
    @field:SerializedName("deg")
    val deg: Double?
)