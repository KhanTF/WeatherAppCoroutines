package com.rage.weatherapp.data.network.weather.pojo

import com.google.gson.annotations.SerializedName

data class CoordinateItem(
    @field:SerializedName("lat")
    val lat: Double?,
    @field:SerializedName("lon")
    val lon: Double?
)