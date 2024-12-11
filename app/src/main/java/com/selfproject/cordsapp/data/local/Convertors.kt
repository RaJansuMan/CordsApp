package com.selfproject.cordsapp.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.selfproject.cordsapp.domain.model.Layer
import java.sql.Date

class Convertors {
    @TypeConverter
    fun fromLayer(layer: Layer): String {
        return Gson().toJson(layer)
    }

    @TypeConverter
    fun toLayer(layerJson: String): Layer {
        return Gson().fromJson(layerJson, Layer::class.java)
    }

    @TypeConverter
    fun fromLayerList(layerList: List<Layer>): String {
        return Gson().toJson(layerList)
    }

    @TypeConverter
    fun toLayerList(layerListJson: String): List<Layer> {
        val type = object : TypeToken<List<Layer>>() {}.type
        return Gson().fromJson(layerListJson, type)
    }

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

}