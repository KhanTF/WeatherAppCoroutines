package com.rage.weatherapp.presentation.ui.cityweather

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.view.ViewCompat
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.rage.weatherapp.R
import com.rage.weatherapp.presentation.base.BaseActivity
import com.rage.weatherapp.presentation.base.getByScope
import com.rage.weatherapp.presentation.common.getMarginLayoutParams
import com.rage.weatherapp.presentation.common.setSelectMargin
import com.rage.weatherapp.presentation.common.setSelectPadding
import com.rage.weatherapp.presentation.model.CityModel
import com.rage.weatherapp.presentation.model.WeatherModel
import kotlinx.android.synthetic.main.activity_city_weather.*
import org.koin.core.parameter.parametersOf

class CityWeatherActivity : BaseActivity(), CityWeatherView {

    companion object {
        private const val KEY_CITY = "KEY_CITY"

        fun getIntent(context: Context, cityModel: CityModel): Intent {
            return Intent(context, CityWeatherActivity::class.java)
                .putExtra(KEY_CITY, cityModel)
        }

        private fun getCityModel(intent: Intent?): CityModel {
            return intent?.getParcelableExtra(KEY_CITY) ?: throw IllegalArgumentException("CityModel is required")
        }

    }

    @InjectPresenter
    lateinit var presenter: CityWeatherPresenter

    @ProvidePresenter
    fun providePresenter(): CityWeatherPresenter = getByScope { parametersOf(getCityModel(intent)) }

    override val layoutId: Int = R.layout.activity_city_weather

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ViewCompat.setOnApplyWindowInsetsListener(activity_city_weather_root) { _, inset ->
            toolbar.getMarginLayoutParams().setSelectMargin(top = inset.systemWindowInsetTop)
            container.setSelectPadding(bottom = inset.systemWindowInsetBottom)
            inset.consumeSystemWindowInsets()
        }
    }

    override fun showWeather(weatherModel: WeatherModel) {
        name.text = weatherModel.cityName
    }

    override fun setProgressVisibility(visibility: Boolean, isAnimate: Boolean) {
        progress.setVisibility(visibility, isAnimate)
    }

}