package com.rage.weatherapp.presentation.ui

import com.rage.weatherapp.presentation.ui.citylist.CityListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainBuilder {
    @ContributesAndroidInjector
    abstract fun buildCityListFragment(): CityListFragment
}