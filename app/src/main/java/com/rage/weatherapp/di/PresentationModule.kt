package com.rage.weatherapp.di

import androidx.fragment.app.FragmentActivity
import com.rage.weatherapp.presentation.common.navigator.TransitionNavigator
import com.rage.weatherapp.presentation.common.navigator.TransitionSupportAppNavigator
import com.rage.weatherapp.presentation.model.CityModel
import com.rage.weatherapp.presentation.navigator.ScreenFactory
import com.rage.weatherapp.presentation.navigator.impl.DefaultScreenFactory
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

fun prepareNavigationModule() = module {
    single { Cicerone.create() }
    single { DefaultScreenFactory() } bind ScreenFactory::class
    factory { get<Cicerone<Router>>().navigatorHolder }
    factory { get<Cicerone<Router>>().router }
    factory { (activity: FragmentActivity, containerId: Int) -> TransitionSupportAppNavigator(activity, containerId) } bind TransitionNavigator::class
}

fun prepareUiModule() = module {
    scope(named<CityListActivity>()) {
        scoped {
            CityListPresenter(
                getCityListUseCase = get(),
                router = get(),
                screenFactory =get()
            )
        }
    }
    scope(named<CityWeatherActivity>()) {
        scoped { (cityModel: CityModel) ->
            CityWeatherPresenter(
                cityModel = cityModel,
                getCityWeatherUseCase = get(),
                router = get()
            )
        }
    }
}
