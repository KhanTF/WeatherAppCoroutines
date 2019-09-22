package com.rage.weatherapp.di

import com.rage.weatherapp.R
import com.rage.weatherapp.presentation.common.navigation.SharedSupportAppNavigator
import com.rage.weatherapp.presentation.model.CityModel
import com.rage.weatherapp.presentation.ui.MainActivity
import com.rage.weatherapp.presentation.ui.MainPresenter
import com.rage.weatherapp.presentation.ui.citylist.CityListFragment
import com.rage.weatherapp.presentation.ui.citylist.CityListPresenter
import com.rage.weatherapp.presentation.ui.cityweather.CityWeatherFragment
import com.rage.weatherapp.presentation.ui.cityweather.CityWeatherPresenter
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.experimental.builder.factoryBy
import org.koin.experimental.builder.scopedBy
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import java.util.*

fun prepareNavigationModule() = module {
    single { Cicerone.create() }
    factory { get<Cicerone<Router>>().navigatorHolder }
    factory { get<Cicerone<Router>>().router }
}
fun prepareUiModule() = module {
    scope(named<MainActivity>()) {
        scoped { MainPresenter(get()) }
        scoped { (mainActivity: MainActivity) -> SharedSupportAppNavigator(mainActivity, R.id.main_container)} bind Navigator::class
    }
    scope(named<CityListFragment>()) {
        scoped { CityListPresenter(get(), get()) }
    }
    scope(named<CityWeatherFragment>()) {
        scoped { (cityModel: CityModel) ->
            CityWeatherPresenter(cityModel, get())
        }
    }
}
