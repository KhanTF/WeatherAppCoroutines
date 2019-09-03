package com.rage.weatherapp.data.network.weather.pojo

import com.google.gson.annotations.SerializedName

data class WeatherCloudItem(
    @field:SerializedName("all")
    val all: Double?
)