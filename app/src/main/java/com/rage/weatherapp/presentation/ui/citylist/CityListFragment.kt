package com.rage.weatherapp.presentation.ui.citylist

import android.os.Bundle
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.rage.weatherapp.R
import com.rage.weatherapp.presentation.base.BaseFragment
import com.rage.weatherapp.presentation.common.itemdecorator.MarginItemDecoration
import com.rage.weatherapp.presentation.model.CityModel
import com.rage.weatherapp.util.executor.CoroutineExecutor
import kotlinx.android.synthetic.main.fragment_city_list.*
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Provider


class CityListFragment : BaseFragment(), CityListView {
    override val layoutId: Int
        get() = R.layout.fragment_city_list
    @Inject
    lateinit var presenterProvider: Provider<CityListPresenter>
    @InjectPresenter
    lateinit var presenter: CityListPresenter

    @ProvidePresenter
    fun providePresenter(): CityListPresenter = presenterProvider.get()

    private val adapter = CityListAdapter()

    companion object {
        private const val PAGE_SIZE = 100
        fun getInstance() = CityListFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        city_list.adapter = adapter
        city_list.layoutManager = LinearLayoutManager(context)
        city_list.addItemDecoration(
            MarginItemDecoration(
                requireContext(),
                R.dimen.list_item_vertical,
                R.dimen.list_item_horizontal,
                R.dimen.list_item_space
            )
        )
    }

    override fun showErrorMessage(message: String) {
    }

    override fun setCityDataSource(source: suspend (offset: Int, limit: Int) -> List<CityModel>) {
        val dataSource = CityListDataSource(this, source)
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(PAGE_SIZE)
            .build()
        val pagedList: PagedList<CityModel> = PagedList.Builder(dataSource, pagedListConfig)
            .setFetchExecutor(CoroutineExecutor(dispatcher = Dispatchers.IO))
            .setNotifyExecutor(CoroutineExecutor(dispatcher =  Dispatchers.Main))
            .build()
        adapter.submitList(pagedList)
    }

}