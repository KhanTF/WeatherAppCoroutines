package com.rage.weatherapp.presentation.ui.citylist

import com.arellomobile.mvp.InjectViewState
import com.rage.weatherapp.domain.usecase.GetCityListUseCase
import com.rage.weatherapp.presentation.base.BasePresenter
import com.rage.weatherapp.presentation.model.CityModelMapper
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Inject

@InjectViewState
class CityListPresenter @Inject constructor(private val getCityListUseCase: GetCityListUseCase) :
    BasePresenter<CityListView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setCityDataSource { offset, limit -> getCityListUseCase.getCityList(offset, limit).map(CityModelMapper::map) }
    }

}