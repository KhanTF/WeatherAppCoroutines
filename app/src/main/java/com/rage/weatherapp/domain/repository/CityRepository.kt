package com.rage.weatherapp.domain.repository

import com.rage.weatherapp.domain.entity.CityEntity

interface CityRepository{
    suspend fun getCityList(offset: Int, count: Int): List<CityEntity>
    suspend fun getCityList(prefix: String,offset: Int, count: Int): List<CityEntity>
    suspend fun getCityById(id: Long): CityEntity?
}