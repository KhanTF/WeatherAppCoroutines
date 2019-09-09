package com.rage.weatherapp.di

import com.rage.weatherapp.data.repository.CityRepositoryImpl
import com.rage.weatherapp.data.repository.WeatherRepositoryImpl
import com.rage.weatherapp.domain.repository.CityRepository
import com.rage.weatherapp.domain.repository.WeatherRepository
import com.rage.weatherapp.domain.usecase.GetCityListUseCase
import com.rage.weatherapp.domain.usecase.GetCityWeatherUseCase
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

fun prepareUseCaseModule() = module {
    factory { GetCityWeatherUseCase(get()) }
    factory { GetCityListUseCase(get()) }
}

fun prepareRepositoryModule() = module {
    single { CityRepositoryImpl(get()) } bind CityRepository::class
    single { WeatherRepositoryImpl(get()) } bind WeatherRepository::class
}
