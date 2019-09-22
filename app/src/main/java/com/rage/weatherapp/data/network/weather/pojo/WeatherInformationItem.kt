package com.rage.weatherapp.data.network.weather.pojo

import com.google.gson.annotations.SerializedName
import java.util.*

data class WeatherInformationItem(
        @field:SerializedName("name")
        val cityName: String?,
        @field:SerializedName("coord")
        val coordinate: CoordinateItem?,
        @field:SerializedName("weather")
        val weather: List<WeatherItem>?,
        @field:SerializedName("base")
        val base: String?,
        @field:SerializedName("main")
        val main: WeatherMainItem?,
        @field:SerializedName("wind")
        val wind: WeatherWindItem?,
        @field:SerializedName("dt")
        val date: Date?,
        @field:SerializedName("clouds")
        val clouds: WeatherCloudItem?,
        @field:SerializedName("timezone")
        val timezone: Long = 0L
)