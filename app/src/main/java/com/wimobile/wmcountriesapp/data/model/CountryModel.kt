package com.wimobile.wmcountriesapp.data.model

import com.google.gson.annotations.SerializedName
import com.wimobile.wmcountriesapp.data.database.entities.CountryEntity
import com.wimobile.wmcountriesapp.data.database.entities.FlagsEntity
import com.wimobile.wmcountriesapp.domain.model.CountryDomain
import com.wimobile.wmcountriesapp.domain.model.FlagsDomain
import com.wimobile.wmcountriesapp.domain.model.NameDomain

data class CountryModel(
    //Main data
    @SerializedName("name") val name: NameModel? = NameModel(),
    @SerializedName("flags") val flags: FlagsModel? = FlagsModel(),
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

data class NameModel(
    @SerializedName("common") val common: String? = null,
    @SerializedName("official") val official: String? = null,
)

data class FlagsModel(
    @SerializedName("png") val png: String? = null,
    @SerializedName("svg") val svg: String? = null,
    @SerializedName("alt") val alt: String? = null
)

/////////// mapper Entity -> Model ///////////////////

fun CountryEntity.toModel() = CountryModel(
    name = NameModel(common = commonName, official = officialName),
    flags = FlagsModel(png = flagPng, svg = flagSvg, alt = flagAlt),
    capital = capital,
    area = area,
    population = population,
    region = region,
    subregion = subregion,
    borders = borders,
    fifa = fifa
)

/////////// mapper Domain -> Model ///////////////////

fun CountryDomain.toModel() = CountryModel(
    name = name?.toModel(),
    flags = flags?.toModel(),
    capital = capital,
    area = area,
    population = population,
    region = region,
    subregion = subregion,
    borders = borders,
    fifa = fifa
)

fun NameDomain.toModel() = NameModel(
    common = common,
    official = official,
)

fun FlagsDomain.toModel() = FlagsModel(
    png = png, svg = svg, alt = alt
)

