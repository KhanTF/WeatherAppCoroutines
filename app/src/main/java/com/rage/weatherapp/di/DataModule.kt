package com.rage.weatherapp.di

import androidx.room.Room
import com.rage.weatherapp.data.db.city.CityDatabase
import com.rage.weatherapp.data.network.weather.WeatherApi
import com.rage.weatherapp.data.network.weather.WeatherApiErrorHandler
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.dsl.module
import timber.log.Timber

const val TAG_NETWORK = "network_traffic"

fun prepareDataBaseModule(): Module = module {
    single {
        Room
            .databaseBuilder(get(), CityDatabase::class.java, "city.db")
            .createFromAsset("city.db")
            .build()
    }
    factory { get<CityDatabase>().getCityDao() }
}

fun prepareNetworkModule(): Module = module {
    single {
        val logger = object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Timber.tag(TAG_NETWORK).i(message)
            }
        }
        OkHttpClient.Builder()
            .addNetworkInterceptor(HttpLoggingInterceptor(logger).apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }
    single {
        WeatherApi.getInstance(get())
    }
}
