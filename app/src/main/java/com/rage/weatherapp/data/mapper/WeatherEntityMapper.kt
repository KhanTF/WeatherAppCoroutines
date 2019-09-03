package com.rage.weatherapp.data.mapper

import com.rage.weatherapp.data.network.weather.pojo.WeatherInformationItem
import com.rage.weatherapp.domain.entity.WeatherEntity

object WeatherEntityMapper{

    fun map(weatherInformationItem: WeatherInformationItem): WeatherEntity{
        val lat = requireNotNull(weatherInformationItem.coordinate?.lat)
        val lon  = requireNotNull(weatherInformationItem.coordinate?.lon)
        val date = requireNotNull(weatherInformationItem.date)
        val temp = requireNotNull(weatherInformationItem.main?.temp)
        val tempMin = requireNotNull(weatherInformationItem.main?.tempMin)
        val tempMax = requireNotNull(weatherInformationItem.main?.tempMax)
        val pressure = requireNotNull(weatherInformationItem.main?.pressure)
        val humidity = requireNotNull(weatherInformationItem.main?.humidity)
        val windSpeed = requireNotNull(weatherInformationItem.wind?.speed)
        val windDeg = requireNotNull(weatherInformationItem.wind?.deg)
        return WeatherEntity(date,temp,tempMin,tempMax,pressure,humidity,windSpeed,windDeg,lat,lon)
    }

}