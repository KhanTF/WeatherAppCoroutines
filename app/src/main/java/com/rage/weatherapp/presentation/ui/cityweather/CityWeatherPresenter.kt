package com.rage.weatherapp.presentation.ui.cityweather

import com.arellomobile.mvp.InjectViewState
import com.rage.weatherapp.domain.usecase.GetCityWeatherUseCase
import com.rage.weatherapp.presentation.base.BasePresenter
import com.rage.weatherapp.presentation.model.CityModel
import com.rage.weatherapp.presentation.model.WeatherModelMapper
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@InjectViewState
class CityWeatherPresenter constructor(
    private val cityModel: CityModel,
    private val getCityWeatherUseCase: GetCityWeatherUseCase
) : BasePresenter<CityWeatherView>() {

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
        launch(Dispatchers.Main) {
            viewState.showErrorMessage(throwable.message)
        }
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        launch(exceptionHandler) {
            try {
                viewState.setProgressVisibility(visibility = true, isAnimate = false)
                val model = withContext(Dispatchers.IO) {
                    getCityWeatherUseCase.getWeatherByCityId(cityModel.id).let(WeatherModelMapper::map)
                }
                viewState.showWeather(model)
            } finally {
                viewState.setProgressVisibility(visibility = false, isAnimate = true)
            }
        }
    }

}