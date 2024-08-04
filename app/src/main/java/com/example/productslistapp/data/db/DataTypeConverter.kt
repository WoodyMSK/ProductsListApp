package com.example.productslistapp.data.db

import androidx.room.TypeConverter
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson
import java.lang.reflect.Type


object DataTypeConverter {
    @TypeConverter
    fun fromString(value: String?): List<String> {
        val listType: Type? = object : TypeToken<List<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<String?>?): String {
        val gson: Gson = Gson()
        val json: String = gson.toJson(list)
        return json
    }
}