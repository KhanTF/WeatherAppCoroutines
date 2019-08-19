package com.rage.weatherapp.di.domain

import com.rage.weatherapp.data.repository.CityRepositoryImpl
import com.rage.weatherapp.domain.repository.CityRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryBinder{
    @Binds
    abstract fun bindCityRepository(cityRepositoryImpl: CityRepositoryImpl): CityRepository
}