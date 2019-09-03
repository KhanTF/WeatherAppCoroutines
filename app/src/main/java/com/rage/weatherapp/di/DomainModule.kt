package com.rage.weatherapp.di

import com.rage.weatherapp.data.repository.CityRepositoryImpl
import com.rage.weatherapp.data.repository.WeatherRepositoryImpl
import com.rage.weatherapp.domain.repository.CityRepository
import com.rage.weatherapp.domain.repository.WeatherRepository
import com.rage.weatherapp.domain.usecase.GetCityListUseCase
import com.rage.weatherapp.domain.usecase.GetCityWeatherUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

object DomainModule : ModuleRather {

    private val usecaseModule = module {
        factory { GetCityWeatherUseCase(get()) }
        factory { GetCityListUseCase(get()) }
    }

    private val repositoryModule = module {
        single { CityRepositoryImpl(get()) as CityRepository }
        single { WeatherRepositoryImpl(get()) as WeatherRepository }
    }

    override fun getModules(): List<Module> {
        return listOf(usecaseModule, repositoryModule)
    }

}