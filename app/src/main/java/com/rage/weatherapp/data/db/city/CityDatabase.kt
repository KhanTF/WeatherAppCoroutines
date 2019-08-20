package com.rage.weatherapp.data.db.city

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rage.weatherapp.data.db.city.dao.CityDao
import com.rage.weatherapp.data.db.city.dto.CityDto

@Database(entities = [CityDto::class], version = 1)
abstract class CityDatabase : RoomDatabase(){
    abstract fun getCityDao() : CityDao
}