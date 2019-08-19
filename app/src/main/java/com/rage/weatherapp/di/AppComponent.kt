package com.rage.weatherapp.di

import android.app.Application
import com.rage.weatherapp.WeatherApplication
import com.rage.weatherapp.di.data.DataModule
import com.rage.weatherapp.di.domain.DomainModule
import com.rage.weatherapp.di.presentation.PresentationModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AndroidInjectionModule::class,
        AppModule::class,
        DataModule::class,
        DomainModule::class,
        PresentationModule::class
    ]
)
@Singleton
interface AppComponent : AndroidInjector<WeatherApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<WeatherApplication>() {
        @BindsInstance
        abstract fun application(application: Application): Builder
    }

}