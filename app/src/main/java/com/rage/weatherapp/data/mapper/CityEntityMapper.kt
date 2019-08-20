package com.rage.weatherapp.data.mapper

import com.rage.weatherapp.data.db.city.dto.CityDto
import com.rage.weatherapp.domain.entity.CityEntity

object CityEntityMapper{
    fun map(city: CityDto): CityEntity = city.run {
        CityEntity(id,name,country, lat, lon)
    }
}