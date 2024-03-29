package com.rage.weatherapp.presentation.ui.citylist

import androidx.paging.PositionalDataSource
import com.rage.weatherapp.presentation.model.CityModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

fun CoroutineScope.createCityListDataSource(storage: suspend (offset: Int, limit: Int) -> List<CityModel>) :CityListDataSource{
    return CityListDataSource(this,storage)
}

class CityListDataSource(private val scope: CoroutineScope,
                         private val storage: suspend (offset: Int, limit: Int) -> List<CityModel>) : PositionalDataSource<CityModel>(), CoroutineScope by scope {

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<CityModel>) {
        launch(Dispatchers.IO) {
            Timber.v("Load range start = %d, size = %d",params.startPosition,params.loadSize)
            val cityList = storage.invoke(params.startPosition, params.loadSize)
            launch(Dispatchers.Main) {
                callback.onResult(cityList)
            }
        }
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<CityModel>) {
        launch(Dispatchers.IO) {
            Timber.v("Load initial start = %d, size = %d",0,params.pageSize)
            val cityList = storage.invoke(0, params.pageSize)
            launch(Dispatchers.Main) {
                callback.onResult(cityList, 0)
            }
        }
    }

}