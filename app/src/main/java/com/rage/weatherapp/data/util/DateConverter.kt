package com.rage.weatherapp.data.util

import com.google.gson.*
import java.lang.reflect.Type
import java.util.*

object DateConverter : JsonSerializer<Date>, JsonDeserializer<Date> {

    override fun serialize(src: Date?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        return JsonPrimitive(src?.time ?: 0L)
    }

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Date {
        val timeInMillis = json?.asLong ?: 0L
        return Date(timeInMillis)
    }

}