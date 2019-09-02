package com.rage.weatherapp.data.db.city.dao

import androidx.room.Dao
import androidx.room.Query
import com.rage.weatherapp.data.db.city.dto.CityDto

@Dao
interface CityDao {
    @Query("SELECT * FROM city ORDER BY name LIMIT :count OFFSET :offset")
    fun getCity(offset: Int, count: Int): List<CityDto>
    @Query("SELECT * FROM city WHERE name LIKE :prefix ORDER BY name LIMIT :count OFFSET :offset")
    fun getCity(prefix: String, offset: Int, count: Int): List<CityDto>
    @Query("SELECT * FROM city WHERE id=:id")
    fun getCityById(id: Long): CityDto?
    @Query("SELECT * FROM city WHERE country=:country ORDER BY name LIMIT :count OFFSET :offset")
    fun getCityByCountry(country: String, offset: Int, count: Int): List<CityDto>
}