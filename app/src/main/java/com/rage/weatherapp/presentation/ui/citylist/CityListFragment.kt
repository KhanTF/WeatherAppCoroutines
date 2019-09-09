package com.rage.weatherapp.presentation.ui.citylist

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.rage.weatherapp.R
import com.rage.weatherapp.presentation.base.BaseFragment
import com.rage.weatherapp.presentation.base.getByScope
import com.rage.weatherapp.presentation.base.injectByScope
import com.rage.weatherapp.presentation.common.itemdecorator.MarginItemDecoration
import com.rage.weatherapp.presentation.model.CityModel
import com.rage.weatherapp.presentation.ui.MainPresenter
import com.rage.weatherapp.util.executor.CoroutineExecutor
import kotlinx.android.synthetic.main.fragment_city_list.*
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.currentScope


class CityListFragment : BaseFragment(), CityListView {
    override val layoutId: Int
        get() = R.layout.fragment_city_list
    @InjectPresenter
    lateinit var presenter: CityListPresenter
    @ProvidePresenter
    fun providePresenter(): CityListPresenter = getByScope()

    private val adapter = CityListAdapter()
    private val onSearchQueryTextListener = object : SearchView.OnQueryTextListener{
        override fun onQueryTextSubmit(query: String?): Boolean {return false}

        override fun onQueryTextChange(newText: String?): Boolean {
            presenter.onSearchCity(newText.orEmpty())
            return true
        }
    }

    companion object {
        private const val PAGE_SIZE = 100
        fun getInstance() = CityListFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_city_list,menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem.actionView as SearchView?
        searchView?.setOnQueryTextListener(onSearchQueryTextListener)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        city_list.adapter = adapter
        adapter.listener = presenter::onCitySelected
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

    override fun setCityDataSource(source: suspend (offset: Int, limit: Int) -> List<CityModel>) {
        val dataSource = createCityListDataSource(source)
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