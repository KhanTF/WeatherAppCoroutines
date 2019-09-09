package com.rage.weatherapp.presentation.ui

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.rage.weatherapp.R
import com.rage.weatherapp.presentation.base.BaseActivity
import com.rage.weatherapp.presentation.base.getByScope
import com.rage.weatherapp.presentation.base.injectByScope
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder

class MainActivity : BaseActivity(), MainView {
    override val layoutId: Int
        get() = R.layout.activity_main

    private val navigator: Navigator by injectByScope{ parametersOf(this)}
    private val navigatorHolder: NavigatorHolder by inject()
    @InjectPresenter
    lateinit var presenter: MainPresenter
    @ProvidePresenter
    fun providePresenter(): MainPresenter = getByScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        presenter.onFirstViewAttach(savedInstanceState != null)
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

}
