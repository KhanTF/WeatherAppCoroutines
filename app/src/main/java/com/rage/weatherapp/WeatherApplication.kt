package com.rage.weatherapp

import android.app.Application
import com.rage.weatherapp.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class WeatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
        startKoin {
            androidLogger()
            androidContext(this@WeatherApplication)
            modules(
                prepareDataBaseModule() +
                        prepareNetworkModule() +
                        prepareUseCaseModule() +
                        prepareRepositoryModule() +
                        prepareNavigationModule() +
                        prepareUiModule()
            )
        }
    }

}