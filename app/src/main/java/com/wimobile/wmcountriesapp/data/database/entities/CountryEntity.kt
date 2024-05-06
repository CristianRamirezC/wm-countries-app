package com.wimobile.wmcountriesapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.wimobile.wmcountriesapp.BuildConfig
import com.wimobile.wmcountriesapp.data.model.CountryModel
import com.wimobile.wmcountriesapp.domain.model.CountryDomain

@Entity(tableName = BuildConfig.COUNTRIES_TABLE_NAME)
data class CountryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,

    @ColumnInfo(name = "common_name") val commonName: String? = null,
    @ColumnInfo(name = "official_name") val officialName: String? = null,

    @ColumnInfo(name = "flag_png") val flagPng: String? = null,
    @ColumnInfo(name = "flag_svg") val flagSvg: String? = null,
    @ColumnInfo(name = "flag_alt") val flagAlt: String? = null,

    @ColumnInfo("capital") val capital: List<String> = listOf(),

    //extra info
    @ColumnInfo("area") val area: Double? = null,
    @ColumnInfo("population") val population: Int? = null,
    @ColumnInfo("region") val region: String? = null,
    @ColumnInfo("subregion") val subregion: String? = null,

    //Optional data
    @ColumnInfo("borders") val borders: List<String> = listOf(),
    @ColumnInfo("fifa") val fifa: String? = null,
)

data class FlagsEntity(
    @SerializedName("png") var png: String? = null,
    @SerializedName("svg") var svg: String? = null,
    @SerializedName("alt") var alt: String? = null
)

//////////////// mapper Model -> Entity ////////////////////////

fun CountryModel.toEntity() = CountryEntity(
    commonName = name?.common,
    officialName = name?.official,
    capital = capital,
    area = area,
    population = population,
    region = region,
    subregion = subregion,
    borders = borders,
    fifa = fifa,
    flagSvg = flags?.svg,
    flagPng = flags?.png,
    flagAlt = flags?.alt
)

//////////////// mapper Domain -> Entity ////////////////////////

fun CountryDomain.toEntity() = CountryEntity(
    commonName = name?.common,
    officialName = name?.official,
    flagSvg = flags?.svg,
    flagPng = flags?.png,
    flagAlt = flags?.alt,
    capital = capital,
    area = area,
    population = population,
    region = region,
    subregion = subregion,
    borders = borders,
    fifa = fifa
)
