package com.rage.weatherapp.data.db.city.dao

import androidx.room.Query
import com.rage.weatherapp.data.db.city.dto.CityDto

interface CityDao{
    @Query("SELECT * FROM city WHERE ORDER BY name")
    fun getCity(offset: Int, count: Int) : List<CityDto>
}