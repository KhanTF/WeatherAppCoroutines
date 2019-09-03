package com.rage.weatherapp.presentation.ui.cityweather

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.rage.weatherapp.presentation.base.BaseView
import com.rage.weatherapp.presentation.model.WeatherModel

interface CityWeatherView : BaseView{
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showWeather(weatherModel: WeatherModel)
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setProgressVisibility(visibility: Boolean)
}