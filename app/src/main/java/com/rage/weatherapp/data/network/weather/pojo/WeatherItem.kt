package com.rage.weatherapp.data.network.weather.pojo

import com.google.gson.annotations.SerializedName

data class WeatherItem(
    @field:SerializedName("id")
    val id: Long?,
    @field:SerializedName("main")
    val main: String?,
    @field:SerializedName("description")
    val description: String?,
    @field:SerializedName("icon")
    val icon: String?)