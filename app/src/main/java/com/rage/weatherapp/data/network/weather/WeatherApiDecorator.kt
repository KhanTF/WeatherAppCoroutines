package com.rage.weatherapp.data.network.weather

import com.rage.weatherapp.data.network.weather.pojo.WeatherInformationItem

abstract class WeatherApiDecorator(private val weatherApi: WeatherApi) : WeatherApi{
    override suspend fun getWeatherByCityId(id: Long, key: String): WeatherInformationItem {
        return weatherApi.getWeatherByCityId(id, key)
    }
}