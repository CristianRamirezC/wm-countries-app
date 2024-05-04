package com.wimobile.wmcountriesapp.data.model

import com.google.gson.annotations.SerializedName

data class CountryModel (
    @SerializedName("name") var name: Name? = Name(),
    @SerializedName("tld") var tld: ArrayList<String> = arrayListOf(),
    @SerializedName("cca2") var cca2: String? = null,
    @SerializedName("ccn3") var ccn3: String? = null,
    @SerializedName("cca3") var cca3: String? = null,
    @SerializedName("cioc") var cioc: String? = null,
    @SerializedName("independent") var independent: Boolean? = null,
    @SerializedName("status") var status: String? = null,
    @SerializedName("unMember") var unMember: Boolean? = null,
    @SerializedName("currencies") var currencies: Currencies? = Currencies(),
    @SerializedName("idd") var idd: Idd? = Idd(),
    @SerializedName("capital") var capital: ArrayList<String> = arrayListOf(),
    @SerializedName("altSpellings") var altSpellings: ArrayList<String> = arrayListOf(),
    @SerializedName("region") var region: String? = null,
    @SerializedName("subregion") var subregion: String? = null,
    @SerializedName("languages") var languages: Languages? = Languages(),
    @SerializedName("translations") var translations: Translations? = Translations(),
    @SerializedName("latlng") var latlng: ArrayList<Int> = arrayListOf(),
    @SerializedName("landlocked") var landlocked: Boolean? = null,
    @SerializedName("borders") var borders: ArrayList<String> = arrayListOf(),
    @SerializedName("area") var area: Int? = null,
    @SerializedName("demonyms") var demonyms: Demonyms? = Demonyms(),
    @SerializedName("flag") var flag: String? = null,
    @SerializedName("maps") var maps: Maps? = Maps(),
    @SerializedName("population") var population: Int? = null,
    @SerializedName("gini") var gini: Map<String, Double>? = null,
    @SerializedName("fifa") var fifa: String? = null,
    @SerializedName("car") var car: Car? = Car(),
    @SerializedName("timezones") var timezones: ArrayList<String> = arrayListOf(),
    @SerializedName("continents") var continents: ArrayList<String> = arrayListOf(),
    @SerializedName("flags") var flags: Flags? = Flags(),
    @SerializedName("coatOfArms") var coatOfArms: CoatOfArms? = CoatOfArms(),
    @SerializedName("startOfWeek") var startOfWeek: String? = null,
    @SerializedName("capitalInfo") var capitalInfo: CapitalInfo? = CapitalInfo(),
    @SerializedName("postalCode") var postalCode: PostalCode? = PostalCode()
)

data class Ron(
    @SerializedName("official") var official: String? = null,
    @SerializedName("common") var common: String? = null
)

data class NativeName(
    @SerializedName("ron") var ron: Ron? = Ron()
)

data class Name(
    @SerializedName("common") var common: String? = null,
    @SerializedName("official") var official: String? = null,
    @SerializedName("nativeName") var nativeName: NativeName? = NativeName()
)

data class MDL(
    @SerializedName("name") var name: String? = null,
    @SerializedName("symbol") var symbol: String? = null
)

data class Currencies(
    @SerializedName("MDL") var MDL: MDL? = MDL()
)

data class Idd(
    @SerializedName("root") var root: String? = null,
    @SerializedName("suffixes") var suffixes: ArrayList<String> = arrayListOf()
)

data class Languages(
    @SerializedName("ron") var ron: String? = null
)

data class Translations(
    @SerializedName("ara") var ara: Language? = Language(),
    @SerializedName("bre") var bre: Language? = Language(),
    @SerializedName("ces") var ces: Language? = Language(),
    @SerializedName("cym") var cym: Language? = Language(),
    @SerializedName("deu") var deu: Language? = Language(),
    @SerializedName("est") var est: Language? = Language(),
    @SerializedName("fin") var fin: Language? = Language(),
    @SerializedName("fra") var fra: Language? = Language(),
    @SerializedName("hrv") var hrv: Language? = Language(),
    @SerializedName("hun") var hun: Language? = Language(),
    @SerializedName("ita") var ita: Language? = Language(),
    @SerializedName("jpn") var jpn: Language? = Language(),
    @SerializedName("kor") var kor: Language? = Language(),
    @SerializedName("nld") var nld: Language? = Language(),
    @SerializedName("per") var per: Language? = Language(),
    @SerializedName("pol") var pol: Language? = Language(),
    @SerializedName("por") var por: Language? = Language(),
    @SerializedName("rus") var rus: Language? = Language(),
    @SerializedName("slk") var slk: Language? = Language(),
    @SerializedName("spa") var spa: Language? = Language(),
    @SerializedName("srp") var srp: Language? = Language(),
    @SerializedName("swe") var swe: Language? = Language(),
    @SerializedName("tur") var tur: Language? = Language(),
    @SerializedName("urd") var urd: Language? = Language(),
    @SerializedName("zho") var zho: Language? = Language()
)

data class Language(
    @SerializedName("official") var official: String? = null,
    @SerializedName("common") var common: String? = null
)

data class DemonymsEng(
    @SerializedName("f") var f: String? = null,
    @SerializedName("m") var m: String? = null
)

data class DemonymsFra(
    @SerializedName("f") var f: String? = null,
    @SerializedName("m") var m: String? = null
)

data class Demonyms(
    @SerializedName("eng") var eng: DemonymsEng? = DemonymsEng(),
    @SerializedName("fra") var fra: DemonymsFra? = DemonymsFra()
)

data class Maps(
    @SerializedName("googleMaps") var googleMaps: String? = null,
    @SerializedName("openStreetMaps") var openStreetMaps: String? = null
)


data class Car(
    @SerializedName("signs") var signs: ArrayList<String> = arrayListOf(),
    @SerializedName("side") var side: String? = null
)

data class Flags(
    @SerializedName("png") var png: String? = null,
    @SerializedName("svg") var svg: String? = null,
    @SerializedName("alt") var alt: String? = null
)

data class CoatOfArms(
    @SerializedName("png") var png: String? = null,
    @SerializedName("svg") var svg: String? = null
)

data class CapitalInfo(
    @SerializedName("latlng") var latlng: ArrayList<Double> = arrayListOf()
)

data class PostalCode(
    @SerializedName("format") var format: String? = null,
    @SerializedName("regex") var regex: String? = null
)
