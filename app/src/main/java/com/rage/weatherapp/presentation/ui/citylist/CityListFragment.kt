package com.rage.weatherapp.presentation.ui.citylist

import android.os.Build
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.rage.weatherapp.R
import com.rage.weatherapp.presentation.base.BaseFragment
import com.rage.weatherapp.presentation.model.CityModel
import kotlinx.android.synthetic.main.fragment_city_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject
import javax.inject.Provider
import kotlin.collections.ArrayList

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
    private val layoutManager: LinearLayoutManager by lazy { LinearLayoutManager(context) }

    companion object {
        fun getInstance() = CityListFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        city_list.adapter = adapter
        city_list.layoutManager = layoutManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            city_list.setOnScrollChangeListener { _, scrollX, scrollY, _, _ ->
                onCityListScrolled(scrollX, scrollY)
            }
        } else {
            city_list.setOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    onCityListScrolled(dx, dy)
                }
            })
        }
    }

    override fun showErrorMessage(message: String) {

    }

    private fun onCityListScrolled(x: Int, y: Int) {
        val lastVisibleItemPosition = layoutManager.findLastCompletelyVisibleItemPosition()
        if (adapter.itemCount - lastVisibleItemPosition < 10) {
            presenter.onLoadNextPage()
        }
    }

    override fun showProgress(isVisible: Boolean) {

    }

    override fun showCityList(cityPages: Map<Int, List<CityModel>>, pageSize: Int) {
        adapter.setData(cityPages, pageSize)
    }

}