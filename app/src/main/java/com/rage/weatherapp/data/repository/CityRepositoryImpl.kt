package com.rage.weatherapp.data.repository

import com.rage.weatherapp.data.db.city.dao.CityDao
import com.rage.weatherapp.data.mapper.CityEntityMapper
import com.rage.weatherapp.domain.entity.CityEntity
import com.rage.weatherapp.domain.exceptions.CityNotFoundException
import com.rage.weatherapp.domain.repository.CityRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityRepositoryImpl @Inject constructor(private val cityDao: CityDao) : CityRepository {

    override suspend fun getCityList(offset: Int, count: Int): List<CityEntity> {
        return cityDao.getCity(offset, count).map(CityEntityMapper::map)
    }

    override suspend fun getCityById(id: Long): CityEntity? {
        return cityDao.getCityById(id)?.let(CityEntityMapper::map)?:throw CityNotFoundException("City not found by id = $id")
    }

}