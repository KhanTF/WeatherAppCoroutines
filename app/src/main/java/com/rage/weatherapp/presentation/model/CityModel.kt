package com.rage.weatherapp.presentation.model

import android.os.Parcelable
import com.rage.weatherapp.domain.entity.CityEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CityModel(val id: Long, val name: String) : Parcelable

object CityModelMapper{
    fun map(cityEntity: CityEntity) : CityModel = CityModel(cityEntity.id,cityEntity.name)
}