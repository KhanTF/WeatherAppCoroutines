package com.rage.weatherapp.di

import com.rage.weatherapp.R
import com.rage.weatherapp.presentation.model.CityModel
import com.rage.weatherapp.presentation.ui.MainActivity
import com.rage.weatherapp.presentation.ui.MainPresenter
import com.rage.weatherapp.presentation.ui.citylist.CityListFragment
import com.rage.weatherapp.presentation.ui.citylist.CityListPresenter
import com.rage.weatherapp.presentation.ui.cityweather.CityWeatherFragment
import com.rage.weatherapp.presentation.ui.cityweather.CityWeatherPresenter
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator

object PresentationModule : ModuleRather {

    private val navigationModule = module {
        single { Cicerone.create() }
        factory { get<Cicerone<Router>>().navigatorHolder }
        factory { get<Cicerone<Router>>().router }
    }

    private val uiModule = module {
        scope(named<MainActivity>()) {
            scoped {
                factory { MainPresenter(get()) }
                factory { (mainActivity: MainActivity) ->
                    SupportAppNavigator(mainActivity, R.id.main_container)
                }
                scope(named<CityListFragment>()) {
                    scoped {
                        factory { CityListPresenter(get(), get()) }
                    }
                }
                scope(named<CityWeatherFragment>()) {
                    scoped {
                        factory { (cityModel: CityModel) ->
                            CityWeatherPresenter(cityModel, get())
                        }
                    }
                }
            }
        }
    }

    override fun getModules(): List<Module> {
        return listOf(navigationModule, uiModule)
    }

}