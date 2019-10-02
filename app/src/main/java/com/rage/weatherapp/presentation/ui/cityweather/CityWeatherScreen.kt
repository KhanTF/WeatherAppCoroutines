package com.rage.weatherapp.presentation.ui.cityweather

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import com.rage.weatherapp.presentation.model.CityModel
import ru.terrakok.cicerone.android.support.SupportAppScreen

class CityWeatherScreen(private val cityModel: CityModel) : SupportAppScreen(){

    override fun getActivityIntent(context: Context): Intent {
        return CityWeatherActivity.getIntent(context,cityModel)
    }

}