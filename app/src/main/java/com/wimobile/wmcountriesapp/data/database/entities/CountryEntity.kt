package com.wimobile.wmcountriesapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wimobile.wmcountriesapp.BuildConfig
import com.wimobile.wmcountriesapp.data.model.FlagsModel
import com.wimobile.wmcountriesapp.data.model.NameModel

@Entity(tableName = BuildConfig.COUNTRIES_TABLE_NAME)
data class CountryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long? = 0,
    @ColumnInfo("name") var name: NameModel? = NameModel(),
    @ColumnInfo("capital") var capital: List<String> = listOf(),
    @ColumnInfo("flags") var flags: FlagsModel? = FlagsModel(),

    //extra info
    @ColumnInfo("area") var area: Int? = null,
    @ColumnInfo("population") var population: Int? = null,
    @ColumnInfo("region") var region: String? = null,
    @ColumnInfo("subregion") var subregion: String? = null,

    //Optional data
    @ColumnInfo("borders") var borders: List<String> = listOf(),
    @ColumnInfo("fifa") var fifa: String? = null,
)
