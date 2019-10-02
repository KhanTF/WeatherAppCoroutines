package com.rage.weatherapp.presentation.ui.citylist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.rage.weatherapp.R
import com.rage.weatherapp.presentation.base.BaseActivity
import com.rage.weatherapp.presentation.base.getByScope
import com.rage.weatherapp.presentation.common.getMarginLayoutParams
import com.rage.weatherapp.presentation.common.itemdecorator.MarginItemDecoration
import com.rage.weatherapp.presentation.common.setSelectMargin
import com.rage.weatherapp.presentation.common.setSelectPadding
import com.rage.weatherapp.presentation.model.CityModel
import com.rage.weatherapp.util.executor.CoroutineExecutor
import kotlinx.android.synthetic.main.activity_city_list.*
import kotlinx.coroutines.Dispatchers

class CityListActivity : BaseActivity(), CityListView {

    companion object {
        private const val PAGE_SIZE = 100
        fun getIntent(context: Context): Intent {
            return Intent(context, CityListActivity::class.java)
        }
    }

    override val layoutId: Int
        get() = R.layout.activity_city_list
    @InjectPresenter
    lateinit var presenter: CityListPresenter

    @ProvidePresenter
    fun providePresenter(): CityListPresenter = getByScope()

    private val adapter = CityListAdapter()
    private val onSearchQueryTextListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            presenter.onSearchCity(newText.orEmpty())
            return true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        ViewCompat.setOnApplyWindowInsetsListener(activity_city_list_root) { _, inset ->
            toolbar.getMarginLayoutParams().setSelectMargin(top = inset.systemWindowInsetTop)
            city_list.setSelectPadding(bottom = inset.systemWindowInsetBottom)
            inset.consumeSystemWindowInsets()
        }

        val layoutManager = LinearLayoutManager(this)
        city_list.adapter = adapter
        adapter.listener = presenter::onCitySelected
        city_list.layoutManager = layoutManager
        city_list.addItemDecoration(
            MarginItemDecoration(
                this,
                R.dimen.list_item_vertical,
                R.dimen.list_item_horizontal,
                R.dimen.list_item_space
            )
        )
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_city_list, menu)

        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem.actionView as SearchView?
        searchView?.setOnQueryTextListener(onSearchQueryTextListener)
        return true
    }

    override fun setCityDataSource(id: String, source: suspend (offset: Int, limit: Int) -> List<CityModel>) {
        val dataSource = createCityListDataSource(source)
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(PAGE_SIZE)
            .build()
        val pagedList: PagedList<CityModel> = PagedList.Builder(dataSource, pagedListConfig)
            .setFetchExecutor(CoroutineExecutor(dispatcher = Dispatchers.IO))
            .setNotifyExecutor(CoroutineExecutor(dispatcher = Dispatchers.Main))
            .build()
        adapter.submitList(id, pagedList)
    }

}