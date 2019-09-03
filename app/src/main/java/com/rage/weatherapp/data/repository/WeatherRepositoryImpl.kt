package com.rage.weatherapp.data.repository

import com.rage.weatherapp.data.mapper.WeatherEntityMapper
import com.rage.weatherapp.data.network.weather.WeatherApi
import com.rage.weatherapp.domain.entity.WeatherEntity
import com.rage.weatherapp.domain.repository.WeatherRepository

class WeatherRepositoryImpl constructor(private val weatherApi: WeatherApi) : WeatherRepository{

    override suspend fun getWeather(cityId: Long): WeatherEntity {
        return weatherApi.getWeatherByCityId(cityId).let(WeatherEntityMapper::map)
    }

}