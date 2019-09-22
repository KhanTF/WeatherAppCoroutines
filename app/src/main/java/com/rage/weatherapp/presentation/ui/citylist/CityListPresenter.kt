package com.rage.weatherapp.presentation.ui.citylist

import com.arellomobile.mvp.InjectViewState
import com.rage.weatherapp.domain.usecase.GetCityListUseCase
import com.rage.weatherapp.presentation.base.BasePresenter
import com.rage.weatherapp.presentation.common.navigation.SharedSupportAppNavigator
import com.rage.weatherapp.presentation.common.navigation.SharedSupportAppScreen
import com.rage.weatherapp.presentation.model.CityModel
import com.rage.weatherapp.presentation.model.CityModelMapper
import com.rage.weatherapp.presentation.ui.cityweather.CityWeatherScreen
import com.rage.weatherapp.util.createDebounce
import ru.terrakok.cicerone.Router

@InjectViewState
class CityListPresenter constructor(
        private val getCityListUseCase: GetCityListUseCase,
        private val router: Router) :
        BasePresenter<CityListView>() {

    private val searchDebounce = createDebounce()
    private var searchText: String = ""

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setCityDataSource(searchText) { offset, limit -> getCityListUseCase.getCityList(offset, limit).map(CityModelMapper::map) }
    }

    fun onCitySelected(param: SharedSupportAppNavigator.SharedParams, cityModel: CityModel) {
        router.navigateTo(SharedSupportAppScreen(CityWeatherScreen(cityModel), param))
    }

    fun onSearchCity(text: String) {
        if (searchText != text) {
            searchText = text
            searchDebounce.debounce(200) {
                if (text.isBlank()) {
                    viewState.setCityDataSource(searchText) { offset, limit -> getCityListUseCase.getCityList(offset, limit).map(CityModelMapper::map) }
                } else {
                    viewState.setCityDataSource(searchText) { offset, limit -> getCityListUseCase.getCityList(text, offset, limit).map(CityModelMapper::map) }
                }
            }
        }
    }

}