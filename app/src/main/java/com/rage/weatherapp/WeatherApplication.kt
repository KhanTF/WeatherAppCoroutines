package com.rage.weatherapp

import android.app.Application
import android.content.Context
import com.rage.weatherapp.di.DataModule
import com.rage.weatherapp.di.DomainModule
import com.rage.weatherapp.di.PresentationModule
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.dsl.module
import timber.log.Timber

class WeatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
        startKoin {
            module {
                factory { this@WeatherApplication as Context }
            }
            modules(DataModule.getModules() + DomainModule.getModules() + PresentationModule.getModules())
        }
    }

}