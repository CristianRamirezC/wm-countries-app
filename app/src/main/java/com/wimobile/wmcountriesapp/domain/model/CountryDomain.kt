package com.wimobile.wmcountriesapp.domain.model

import com.google.gson.annotations.SerializedName
import com.wimobile.wmcountriesapp.data.database.entities.CountryEntity
import com.wimobile.wmcountriesapp.data.database.entities.FlagsEntity
import com.wimobile.wmcountriesapp.data.database.entities.NameEntity
import com.wimobile.wmcountriesapp.data.database.entities.NativeNameEntity
import com.wimobile.wmcountriesapp.data.database.entities.RonEntity
import com.wimobile.wmcountriesapp.data.model.CountryModel
import com.wimobile.wmcountriesapp.data.model.FlagsModel
import com.wimobile.wmcountriesapp.data.model.NameModel
import com.wimobile.wmcountriesapp.data.model.NativeNameModel
import com.wimobile.wmcountriesapp.data.model.RonModel

data class CountryDomain(
    //Main data
    @SerializedName("name") var name: NameDomain? = NameDomain(),
    @SerializedName("flags") var flags: FlagsDomain? = FlagsDomain(),
    @SerializedName("capital") var capital: List<String> = listOf(),

    //Extra data
    @SerializedName("area") var area: Int? = null,
    @SerializedName("population") var population: Int? = null,
    @SerializedName("region") var region: String? = null,
    @SerializedName("subregion") var subregion: String? = null,

    //Optional data
    @SerializedName("borders") var borders: List<String> = listOf(),
    @SerializedName("fifa") var fifa: String? = null,
)


data class NameDomain(
    @SerializedName("common") var common: String? = null,
    @SerializedName("official") var official: String? = null,
    @SerializedName("nativeName") var nativeName: NativeNameDomain? = NativeNameDomain()
)

data class NativeNameDomain(
    @SerializedName("ron") var ron: RonDomain? = RonDomain()
)

data class FlagsDomain(
    @SerializedName("png") var png: String? = null,
    @SerializedName("svg") var svg: String? = null,
    @SerializedName("alt") var alt: String? = null
)

data class RonDomain(
    @SerializedName("official") var official: String? = null,
    @SerializedName("common") var common: String? = null
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
    nativeName = nativeName?.toDomain()
)

fun NativeNameModel.toDomain() = NativeNameDomain(
    ron = ron?.toDomain()
)

fun RonModel.toDomain() = RonDomain(
    official = official, common = common
)

fun FlagsModel.toDomain() = FlagsDomain(
    png = png, svg = svg, alt = alt
)

/////////// mapper Entities -> Domain ///////////////////

fun CountryEntity.toDomain() = CountryDomain(
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

fun NameEntity.toDomain() = NameDomain(
    common = common,
    official = official,
    nativeName = nativeName?.toDomain()
)

fun NativeNameEntity.toDomain() = NativeNameDomain(
    ron = ron?.toDomain()
)

fun RonEntity.toDomain() = RonDomain(
    official = official, common = common
)

fun FlagsEntity.toDomain() = FlagsDomain(
    png = png, svg = svg, alt = alt
)





