package com.rage.weatherapp.presentation.ui.cityweather

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.rage.weatherapp.R
import com.rage.weatherapp.presentation.base.BaseFragment
import com.rage.weatherapp.presentation.model.CityModel
import com.rage.weatherapp.presentation.model.WeatherModel
import org.koin.androidx.scope.currentScope
import org.koin.core.parameter.parametersOf

class CityWeatherFragment : BaseFragment(),CityWeatherView{

    companion object{
        private const val KEY_CITY = "KEY_CITY"

        fun getCityModelArgument(cityWeatherFragment: CityWeatherFragment): CityModel{
            return requireNotNull(cityWeatherFragment.arguments?.getParcelable(KEY_CITY))
        }

        fun getInstance(cityModel: CityModel) : CityWeatherFragment{
            val arguments = Bundle().apply {
                putParcelable(KEY_CITY,cityModel)
            }
            return CityWeatherFragment().also {
                it.arguments = arguments
            }
        }
    }

    private val presenterProvider: CityWeatherPresenter by currentScope.inject{
        parametersOf(getCityModelArgument(this))
    }
    @InjectPresenter
    lateinit var presenter: CityWeatherPresenter
    @ProvidePresenter
    fun providePresenter() : CityWeatherPresenter{
        return presenterProvider
    }

    override val layoutId: Int = R.layout.fragment_city_weather

    override fun showWeather(weatherModel: WeatherModel) {

    }

    override fun setProgressVisibility(visibility: Boolean) {

    }

}