package com.rage.weatherapp.domain.usecase

import com.rage.weatherapp.domain.entity.WeatherEntity
import com.rage.weatherapp.domain.repository.WeatherRepository

class GetCityWeatherUseCase constructor(private val weatherRepository: WeatherRepository){

    suspend fun getWeatherByCityId(cityId: Long) : WeatherEntity{
        return weatherRepository.getWeather(cityId)
    }

}