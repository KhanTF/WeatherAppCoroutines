package com.rage.weatherapp.presentation.model

import com.rage.weatherapp.domain.entity.CityEntity

data class CityModel(val id: Long, val name: String)

object CityMapper{
    fun map(cityEntity: CityEntity) : CityModel = CityModel(cityEntity.id,cityEntity.name)
}