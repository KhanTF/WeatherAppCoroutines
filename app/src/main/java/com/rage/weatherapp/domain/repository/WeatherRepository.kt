package com.rage.weatherapp.domain.repository

import com.rage.weatherapp.domain.entity.WeatherEntity

interface WeatherRepository{
    suspend fun getWeather(cityId: Long) : WeatherEntity
}