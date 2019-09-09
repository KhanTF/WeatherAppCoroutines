package com.rage.weatherapp.data.network.weather

import com.google.gson.GsonBuilder
import com.rage.weatherapp.BuildConfig
import com.rage.weatherapp.data.network.weather.pojo.WeatherInformationItem
import com.rage.weatherapp.data.util.DateConverter
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface WeatherApi {
    companion object {
        private const val QUERY_API_KEY = "APPID"

        fun getInstance(client: OkHttpClient): WeatherApi {
            val gson = GsonBuilder()
                .registerTypeAdapter(Date::class.java, DateConverter)
                .create()
            return WeatherApiErrorHandler(
                Retrofit.Builder()
                    .baseUrl(BuildConfig.OPEN_WEATHER_API_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                    .create(WeatherApi::class.java)
            )
        }
    }

    @GET("data/2.5/weather")
    suspend fun getWeatherByCityId(@Query("id") id: Long, @Query(QUERY_API_KEY) key: String = BuildConfig.OPEN_WEATHER_API_KEY): WeatherInformationItem
}