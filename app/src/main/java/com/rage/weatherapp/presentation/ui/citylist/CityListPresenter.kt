package com.rage.weatherapp.presentation.ui.citylist

import com.arellomobile.mvp.InjectViewState
import com.rage.weatherapp.domain.usecase.GetCityListUseCase
import com.rage.weatherapp.presentation.base.BasePresenter
import com.rage.weatherapp.presentation.model.CityModelMapper
import com.rage.weatherapp.util.CoroutineDebounce
import com.rage.weatherapp.util.createDebounce
import javax.inject.Inject

@InjectViewState
class CityListPresenter @Inject constructor(private val getCityListUseCase: GetCityListUseCase) :
    BasePresenter<CityListView>() {

    private val searchDebounce = createDebounce(2000)
    private var searchText: String = ""

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setCityDataSource { offset, limit -> getCityListUseCase.getCityList(offset, limit).map(CityModelMapper::map) }
    }

    fun onSearchCity(text: String){
        if(searchText != text){
            searchText = text
            searchDebounce.debounce {
                if(text.isBlank()){
                    viewState.setCityDataSource { offset, limit -> getCityListUseCase.getCityList(offset, limit).map(CityModelMapper::map) }
                }else {
                    viewState.setCityDataSource { offset, limit -> getCityListUseCase.getCityList(text, offset, limit).map(CityModelMapper::map) }
                }
            }
        }
    }

}