package com.wimobile.wmcountriesapp.domain.model

import com.google.gson.annotations.SerializedName
import com.wimobile.wmcountriesapp.data.database.entities.CountryEntity
import com.wimobile.wmcountriesapp.data.database.entities.FlagsEntity
import com.wimobile.wmcountriesapp.data.model.CountryModel
import com.wimobile.wmcountriesapp.data.model.FlagsModel
import com.wimobile.wmcountriesapp.data.model.NameModel

data class CountryDomain(
    //Main data
    @SerializedName("name") val name: NameDomain? = NameDomain(),
    @SerializedName("flags") val flags: FlagsDomain? = FlagsDomain(),
    @SerializedName("capital") val capital: List<String> = listOf(),

    //Extra data
    @SerializedName("area") val area: Double? = null,
    @SerializedName("population") val population: Int? = null,
    @SerializedName("region") val region: String? = null,
    @SerializedName("subregion") val subregion: String? = null,

    //Optional data
    @SerializedName("borders") val borders: List<String> = listOf(),
    @SerializedName("fifa") val fifa: String? = null,
)

data class NameDomain(
    @SerializedName("common") val common: String? = null,
    @SerializedName("official") val official: String? = null,
)

data class FlagsDomain(
    @SerializedName("png") val png: String? = null,
    @SerializedName("svg") val svg: String? = null,
    @SerializedName("alt") val alt: String? = null
)

/////////// mapper Model-> Domain ///////////////////

fun CountryModel.toDomain() = CountryDomain(
    name = name?.toDomain(),
    flags = flags?.toDomain(),
    capital = capital,
    area = area,
    population = population,
    region = region,
    subregion = subregion,
    borders = borders,
    fifa = fifa
)

fun NameModel.toDomain() = NameDomain(
    common = common,
    official = official,
)

fun FlagsModel.toDomain() = FlagsDomain(
    png = png, svg = svg, alt = alt
)

/////////// mapper Entities -> Domain ///////////////////

fun CountryEntity.toDomain() = CountryDomain(
    name = NameDomain(common = commonName, official = officialName),
    flags = FlagsDomain(png = flagPng, svg = flagSvg, alt = flagAlt),
    capital = capital,
    area = area,
    population = population,
    region = region,
    subregion = subregion,
    borders = borders,
    fifa = fifa
)





