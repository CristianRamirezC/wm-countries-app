package com.wimobile.wmcountriesapp.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.wimobile.wmcountriesapp.data.database.entities.FlagsEntity
import com.wimobile.wmcountriesapp.data.database.entities.NameEntity
import com.wimobile.wmcountriesapp.data.model.FlagsModel
import com.wimobile.wmcountriesapp.data.model.NameModel

class CountryConverters {

    @TypeConverter
    fun toStringList(stringListString: String): List<String> {
        val type = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(stringListString, type)
    }

    @TypeConverter
    fun fromStringList(stringList: List<String>): String {
        val type = object : TypeToken<List<String>>() {}.type
        return Gson().toJson(stringList, type)
    }

    @TypeConverter
    fun toName(nameString: String): NameEntity {
        val type = object : TypeToken<NameEntity>() {}.type
        return Gson().fromJson(nameString, type)
    }

    @TypeConverter
    fun fromName(nameObject: NameEntity): String {
        val type = object : TypeToken<NameEntity>() {}.type
        return Gson().toJson(nameObject, type)
    }

    @TypeConverter
    fun toFlags(flagsString: String): FlagsEntity {
        val type = object :TypeToken<FlagsEntity>(){}.type
        return Gson().fromJson(flagsString, type)
    }

    @TypeConverter
    fun fromFlags(flags: FlagsEntity): String {
        val type = object :TypeToken<FlagsEntity>(){}.type
        return Gson().toJson(flags, type)
    }
}