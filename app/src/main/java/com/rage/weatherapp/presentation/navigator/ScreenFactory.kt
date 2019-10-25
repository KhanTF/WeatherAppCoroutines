package com.rage.weatherapp.presentation.navigator

import com.rage.weatherapp.presentation.model.CityModel
import ru.terrakok.cicerone.Screen

interface ScreenFactory {

    fun getCityListScreen() : Screen
    fun getCityWeatherScreen(cityModel: CityModel) : Screen

}