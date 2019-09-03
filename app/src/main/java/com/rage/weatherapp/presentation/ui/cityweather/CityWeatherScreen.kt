package com.rage.weatherapp.presentation.ui.cityweather

import androidx.fragment.app.Fragment
import com.rage.weatherapp.presentation.model.CityModel
import ru.terrakok.cicerone.android.support.SupportAppScreen

class CityWeatherScreen(private val cityModel: CityModel) : SupportAppScreen(){
    override fun getFragment(): Fragment {
        return CityWeatherFragment.getInstance(cityModel)
    }
}