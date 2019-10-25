package com.rage.weatherapp.presentation.navigator.impl

import com.rage.weatherapp.presentation.model.CityModel
import com.rage.weatherapp.presentation.navigator.ScreenFactory
import com.rage.weatherapp.presentation.ui.citylist.CityListScreen
import com.rage.weatherapp.presentation.ui.cityweather.CityWeatherScreen
import ru.terrakok.cicerone.android.support.SupportAppScreen

class DefaultScreenFactory : ScreenFactory {

    override fun getCityListScreen(): SupportAppScreen = CityListScreen()

    override fun getCityWeatherScreen(cityModel: CityModel): SupportAppScreen = CityWeatherScreen(cityModel)

}