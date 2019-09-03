package com.rage.weatherapp.data.network.weather.pojo

import com.google.gson.annotations.SerializedName

data class WeatherMainItem(
    @field:SerializedName("temp")
    val temp: Double?,
    @field:SerializedName("pressure")
    val pressure: Double?,
    @field:SerializedName("humidity")
    val humidity: Double?,
    @field:SerializedName("temp_min")
    val tempMin: Double?,
    @field:SerializedName("temp_max")
    val tempMax: Double?
)