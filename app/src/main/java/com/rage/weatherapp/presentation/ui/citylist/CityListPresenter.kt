package com.rage.weatherapp.presentation.ui.citylist

import android.util.SparseArray
import com.arellomobile.mvp.InjectViewState
import com.rage.weatherapp.domain.usecase.GetCityListUseCase
import com.rage.weatherapp.presentation.base.BasePresenter
import com.rage.weatherapp.presentation.model.CityMapper
import com.rage.weatherapp.presentation.model.CityModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

@InjectViewState
class CityListPresenter @Inject constructor(private val getCityListUseCase: GetCityListUseCase) :
        BasePresenter<CityListView>() {

    companion object {
        private const val PAGE_SIZE = 20
    }

    private var pages: MutableMap<Int, List<CityModel>> = Collections.synchronizedMap(TreeMap())
    private var page: Int = 0
    private val exceptionHandler = CoroutineExceptionHandler { _, e ->
        e.printStackTrace()
        viewState.showErrorMessage("Error")
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadPage(page++)
    }

    private suspend fun getPage(page: Int): List<CityModel> {
        return getCityListUseCase.getCityList(page * PAGE_SIZE, PAGE_SIZE).map(CityMapper::map)
    }

    private fun loadPage(page: Int) = launch(exceptionHandler) {
        try {
            viewState.showProgress(true)
            withContext(Dispatchers.IO) {
                pages.put(page, getPage(page))
            }
            viewState.showCityList(pages, PAGE_SIZE)
        } finally {
            viewState.showProgress(false)
        }
    }

    fun onLoadNextPage() {
        loadPage(page++)
    }

}