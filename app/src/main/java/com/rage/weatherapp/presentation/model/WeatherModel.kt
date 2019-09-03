package com.rage.weatherapp.presentation.model

import android.os.Parcelable
import com.rage.weatherapp.domain.entity.WeatherEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherModel(val temp: Double, val tempMin: Double, val tempMax: Double) : Parcelable

object WeatherModelMapper {

    fun map(weatherEntity: WeatherEntity): WeatherModel {
        return WeatherModel(
            weatherEntity.temp,
            weatherEntity.tempMin,
            weatherEntity.tempMax
        )
    }

}