package com.rage.weatherapp.domain.usecase

import com.rage.weatherapp.domain.entity.CityEntity
import com.rage.weatherapp.domain.repository.CityRepository
import javax.inject.Inject

class GetCityListUseCase @Inject constructor(private val cityRepository: CityRepository){
    suspend fun getCityList(offset: Int, count: Int): List<CityEntity>{
        return cityRepository.getCityList(offset,count)
    }
}