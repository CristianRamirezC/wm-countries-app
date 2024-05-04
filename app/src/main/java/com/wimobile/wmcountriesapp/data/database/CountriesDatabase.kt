package com.wimobile.wmcountriesapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.wimobile.wmcountriesapp.data.database.dao.CountriesDao
import com.wimobile.wmcountriesapp.data.database.entities.CountryEntity

@Database(
    entities = [CountryEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(CountryConverters::class)
abstract class CountriesDatabase: RoomDatabase() {

    abstract fun getCountriesDao(): CountriesDao
}