package com.rage.weatherapp.data.repository

import android.content.Context
import android.util.JsonReader
import com.rage.weatherapp.R
import com.rage.weatherapp.domain.entity.CityEntity
import com.rage.weatherapp.domain.repository.CityRepository
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityRepositoryImpl @Inject constructor(private val context: Context) : CityRepository {

    companion object {
        private const val KEY_ID = "id"
        private const val KEY_NAME = "name"
    }

    override suspend fun getCityList(offset: Int, count: Int): List<CityEntity> {
        val resources = context.resources
        val jsonReader = JsonReader(BufferedReader(InputStreamReader(resources.openRawResource(R.raw.city))))
        return jsonReader.use { readCityList(jsonReader, offset, count) }
    }

    private fun readCityList(jsonReader: JsonReader, offset: Int, count: Int): List<CityEntity> {
        val list = mutableListOf<CityEntity>()
        jsonReader.beginArray()
        for (i in 0 until offset) {
            jsonReader.skipValue()
        }
        for (i in 0 until count) {
            if (jsonReader.hasNext()) {
                list.add(readCity(jsonReader))
            } else {
                break
            }
        }
        return list
    }

    private fun readCity(jsonReader: JsonReader): CityEntity {
        var id: Long = -1
        var name: String? = null
        jsonReader.beginObject()
        while (jsonReader.hasNext()) {
            when (jsonReader.nextName()) {
                KEY_ID -> id = jsonReader.nextLong()
                KEY_NAME -> name = jsonReader.nextString()
                else ->jsonReader.skipValue()
            }
        }
        jsonReader.endObject()
        if (id > 0 && name != null) {
            return CityEntity(id, name)
        } else {
            throw IllegalArgumentException("Invalid json structure")
        }
    }

}