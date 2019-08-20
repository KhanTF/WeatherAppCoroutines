package com.rage.weatherapp.data.db.city.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city")
data class CityDto(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val country: String,
    val lat: Double,
    val lon: Double)