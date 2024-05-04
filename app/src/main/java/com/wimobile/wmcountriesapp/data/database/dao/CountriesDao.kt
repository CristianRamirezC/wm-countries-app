package com.wimobile.wmcountriesapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wimobile.wmcountriesapp.BuildConfig
import com.wimobile.wmcountriesapp.data.database.entities.CountryEntity

@Dao
interface CountriesDao {

    @Query("SELECT * FROM ${BuildConfig.COUNTRIES_TABLE_NAME}")
    suspend fun getAllCountries(): List<CountryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCountries(countries: List<CountryEntity>)

}