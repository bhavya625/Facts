package com.example.arinspectexercise.model

import android.arch.persistence.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.google.gson.Gson


class DataConverter {

    @TypeConverter
    fun fromFactsItemsList(factsItems: List<FactsItem>?): String? {
        if (factsItems == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<FactsItem>>() {

        }.type
        return gson.toJson(factsItems, type)
    }

    @TypeConverter
    fun toFactsItemsList(factsItemsString: String?): List<FactsItem>? {
        if (factsItemsString == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<FactsItem>>() {

        }.type
        return gson.fromJson(factsItemsString, type)
    }
}