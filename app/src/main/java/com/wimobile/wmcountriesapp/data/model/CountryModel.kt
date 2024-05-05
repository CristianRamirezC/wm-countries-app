package com.wimobile.wmcountriesapp.data.model

import com.google.gson.annotations.SerializedName
import com.wimobile.wmcountriesapp.data.database.entities.CountryEntity
import com.wimobile.wmcountriesapp.data.database.entities.FlagsEntity
import com.wimobile.wmcountriesapp.data.database.entities.NameEntity
import com.wimobile.wmcountriesapp.data.database.entities.RonEntity
import com.wimobile.wmcountriesapp.domain.model.CountryDomain
import com.wimobile.wmcountriesapp.domain.model.FlagsDomain
import com.wimobile.wmcountriesapp.domain.model.NameDomain
import com.wimobile.wmcountriesapp.domain.model.RonDomain

data class CountryModel(
    //Main data
    @SerializedName("name") var name: NameModel? = NameModel(),
    @SerializedName("flags") var flags: FlagsModel? = FlagsModel(),
    @SerializedName("capital") var capital: List<String> = listOf(),

    //Extra data
    @SerializedName("area") var area: Double? = null,
    @SerializedName("population") var population: Int? = null,
    @SerializedName("region") var region: String? = null,
    @SerializedName("subregion") var subregion: String? = null,

    //Optional data
    @SerializedName("borders") var borders: List<String> = listOf(),
    @SerializedName("fifa") var fifa: String? = null,
)

data class RonModel(
    @SerializedName("official") var official: String? = null,
    @SerializedName("common") var common: String? = null
)

//data class NativeNameModel(
//    @SerializedName("ron") var ron: RonModel? = RonModel()
//)

data class NameModel(
    @SerializedName("common") var common: String? = null,
    @SerializedName("official") var official: String? = null,
    @SerializedName("nativeName") var nativeName: Map<String, RonModel>? = null
)

data class FlagsModel(
    @SerializedName("png") var png: String? = null,
    @SerializedName("svg") var svg: String? = null,
    @SerializedName("alt") var alt: String? = null
)

/////////// mapper Entity -> Model ///////////////////

fun CountryEntity.toModel() = CountryModel(
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

fun NameEntity.toModel() = NameModel(
    common = common,
    official = official,
//    nativeName = nativeName?.toModel()
    nativeName = nativeName
)

//fun NativeNameEntity.toModel() = NativeNameModel(
//    ron = ron?.toModel()
//)

fun RonEntity.toModel() = RonModel(
    official = official, common = common
)

fun FlagsEntity.toModel() = FlagsModel(
    png = png, svg = svg, alt = alt
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
//    nativeName = nativeName?.toModel()
    nativeName = nativeName
)

//fun NativeNameDomain.toModel() = NativeNameModel(
//    ron = ron?.toModel()
//)

fun RonDomain.toModel() = RonModel(
    official = official, common = common
)

fun FlagsDomain.toModel() = FlagsModel(
    png = png, svg = svg, alt = alt
)

