package com.wimobile.wmcountriesapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.wimobile.wmcountriesapp.BuildConfig
import com.wimobile.wmcountriesapp.data.model.CountryModel
import com.wimobile.wmcountriesapp.data.model.FlagsModel
import com.wimobile.wmcountriesapp.data.model.NameModel
import com.wimobile.wmcountriesapp.data.model.NativeNameModel
import com.wimobile.wmcountriesapp.data.model.RonModel
import com.wimobile.wmcountriesapp.domain.model.CountryDomain
import com.wimobile.wmcountriesapp.domain.model.FlagsDomain
import com.wimobile.wmcountriesapp.domain.model.NameDomain
import com.wimobile.wmcountriesapp.domain.model.NativeNameDomain
import com.wimobile.wmcountriesapp.domain.model.RonDomain

@Entity(tableName = BuildConfig.COUNTRIES_TABLE_NAME)
data class CountryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long? = 0,
    @ColumnInfo("name") var name: NameEntity? = NameEntity(),
    @ColumnInfo("capital") var capital: List<String> = listOf(),
    @ColumnInfo("flags") var flags: FlagsEntity? = FlagsEntity(),

    //extra info
    @ColumnInfo("area") var area: Int? = null,
    @ColumnInfo("population") var population: Int? = null,
    @ColumnInfo("region") var region: String? = null,
    @ColumnInfo("subregion") var subregion: String? = null,

    //Optional data
    @ColumnInfo("borders") var borders: List<String> = listOf(),
    @ColumnInfo("fifa") var fifa: String? = null,
)

data class RonEntity(
    @SerializedName("official") var official: String? = null,
    @SerializedName("common") var common: String? = null
)

data class NativeNameEntity(
    @SerializedName("ron") var ron: RonEntity? = RonEntity()
)

data class NameEntity(
    @SerializedName("common") var common: String? = null,
    @SerializedName("official") var official: String? = null,
    @SerializedName("nativeName") var nativeName: NativeNameEntity? = NativeNameEntity()
)

data class FlagsEntity(
    @SerializedName("png") var png: String? = null,
    @SerializedName("svg") var svg: String? = null,
    @SerializedName("alt") var alt: String? = null
)


//////////////// mapper Model -> Entity ////////////////////////

fun CountryModel.toEntity() = CountryEntity(
    name = name?.toEntity(),
    flags = flags?.toEntity(),
    capital = capital,
    area = area,
    population = population,
    region = region,
    subregion = subregion,
    borders = borders,
    fifa = fifa
)

fun NameModel.toEntity() = NameEntity(
    common = common,
    official = official,
    nativeName = nativeName?.toEntity()
)

fun NativeNameModel.toEntity() = NativeNameEntity(
    ron = ron?.toEntity()
)

fun RonModel.toEntity() = RonEntity(
    official = official, common = common
)

fun FlagsModel.toEntity() = FlagsEntity(
    png = png, svg = svg, alt = alt
)

//////////////// mapper Domain -> Entity ////////////////////////

fun CountryDomain.toEntity() = CountryEntity(
    name = name?.toEntity(),
    flags = flags?.toEntity(),
    capital = capital,
    area = area,
    population = population,
    region = region,
    subregion = subregion,
    borders = borders,
    fifa = fifa
)

fun NameDomain.toEntity() = NameEntity(
    common = common,
    official = official,
    nativeName = nativeName?.toEntity()
)

fun NativeNameDomain.toEntity() = NativeNameEntity(
    ron = ron?.toEntity()
)

fun RonDomain.toEntity() = RonEntity(
    official = official, common = common
)

fun FlagsDomain.toEntity() = FlagsEntity(
    png = png, svg = svg, alt = alt
)
