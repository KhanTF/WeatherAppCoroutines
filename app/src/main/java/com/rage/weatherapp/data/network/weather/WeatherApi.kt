package com.rage.weatherapp.data.network.weather

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi{
    @GET("data/2.5/weather")
    suspend fun getWeatherByCity(@Query("q") cityName: String)
}