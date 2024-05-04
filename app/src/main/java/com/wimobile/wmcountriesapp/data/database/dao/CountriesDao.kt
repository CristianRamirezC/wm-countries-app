package com.wimobile.wmcountriesapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wimobile.wmcountriesapp.BuildConfig
import com.wimobile.wmcountriesapp.data.database.entities.CountryEntity

private const val tableName = BuildConfig.COUNTRIES_TABLE_NAME

@Dao
interface CountriesDao {

    @Query("SELECT * FROM $tableName")
    suspend fun getAllCountries(): List<CountryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCountries(countries: List<CountryEntity>)

    @Query("SELECT * FROM $tableName WHERE fifa = :fifa")
    suspend fun getCountryByFifa(fifa: String): CountryEntity

    @Query("SELECT * FROM $tableName WHERE name = :name")
    suspend fun getCountryByName(name: String): CountryEntity

}