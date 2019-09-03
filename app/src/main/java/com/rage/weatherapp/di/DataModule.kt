package com.rage.weatherapp.di

import androidx.room.Room
import com.rage.weatherapp.data.db.city.CityDatabase
import com.rage.weatherapp.data.network.weather.WeatherApi
import com.rage.weatherapp.data.network.weather.WeatherApiErrorHandler
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.experimental.builder.singleBy
import timber.log.Timber

object DataModule : ModuleRather {

    private const val TAG_NETWORK = "TAG_NETWORK"

    private val databaseModule = module {
        single {
            Room
                .databaseBuilder(get(), CityDatabase::class.java, "city.db")
                .createFromAsset("city.db")
                .build()
        }
        factory {
            get<CityDatabase>().getCityDao()
        }
    }

    private val networkModule = module {
        single {
            OkHttpClient.Builder()
                .addNetworkInterceptor(HttpLoggingInterceptor(object :
                    HttpLoggingInterceptor.Logger {
                    override fun log(message: String) {
                        Timber.tag(TAG_NETWORK).i(message)
                    }
                }).apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()
        }
        single {
            WeatherApiErrorHandler(WeatherApi.getInstance(get())) as WeatherApi
        }
    }

    override fun getModules(): List<Module> {
        return listOf(databaseModule, networkModule)
    }

}