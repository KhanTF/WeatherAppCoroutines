package com.rage.weatherapp.di

import androidx.fragment.app.FragmentActivity
import com.rage.weatherapp.presentation.model.CityModel
import com.rage.weatherapp.presentation.ui.citylist.CityListActivity
import com.rage.weatherapp.presentation.ui.citylist.CityListPresenter
import com.rage.weatherapp.presentation.ui.cityweather.CityWeatherActivity
import com.rage.weatherapp.presentation.ui.cityweather.CityWeatherPresenter
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator

fun prepareNavigationModule() = module {
    single { Cicerone.create() }
    factory { get<Cicerone<Router>>().navigatorHolder }
    factory { get<Cicerone<Router>>().router }
    factory { (activity: FragmentActivity, containerId: Int) ->
        SupportAppNavigator(activity, containerId)
    } bind Navigator::class
}

fun prepareUiModule() = module {
    scope(named<CityListActivity>()) {
        scoped { CityListPresenter(get(), get()) }
    }
    scope(named<CityWeatherActivity>()) {
        scoped { (cityModel: CityModel) ->
            CityWeatherPresenter(cityModel, get())
        }
    }
}
