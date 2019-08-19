package com.rage.weatherapp.presentation.ui

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.rage.weatherapp.R
import com.rage.weatherapp.presentation.base.BaseActivity
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject
import javax.inject.Provider

class MainActivity : BaseActivity(), MainView {
    override val layoutId: Int
        get() = R.layout.activity_main
    @Inject
    lateinit var presenterProvider: Provider<MainPresenter>
    @Inject
    lateinit var navigatorHolder: NavigatorHolder
    @Inject
    lateinit var navigator: Navigator
    @InjectPresenter
    lateinit var presenter: MainPresenter
    @ProvidePresenter
    fun providePresenter() : MainPresenter = presenterProvider.get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onFirstViewAttach(savedInstanceState!=null)
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
