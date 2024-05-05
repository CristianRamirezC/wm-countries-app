import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs")
}

android {

    buildFeatures {
        buildConfig = true
    }

    namespace = "com.wimobile.wmcountriesapp"
    compileSdk = 34

    val prop = Properties()
    prop.load(FileInputStream("network.properties"))
    prop.load(FileInputStream("database.properties"))

    defaultConfig {



        applicationId = "com.wimobile.wmcountriesapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "BASE_URL", prop.getProperty("BASE_URL"))
        buildConfigField("String", "GET_ALL_COUNTRIES", prop.getProperty("ALL_COUNTRIES"))
        buildConfigField("String", "DATABASE_NAME", prop.getProperty("DATABASE_NAME"))
        buildConfigField("String", "COUNTRIES_TABLE_NAME", prop.getProperty("COUNTRIES_TABLE_NAME"))
    }

    viewBinding{
        enable = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    val lifecycle_version = "2.6.1"
    val hilt_version = "2.48"
    val nav_version = "2.5.0"
    val room_version = "2.6.1"

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation("androidx.fragment:fragment-ktx:1.5.2")

    //Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")

    //Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")

    //RETROFIT
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.google.code.gson:gson:2.8.6") //transformar el json
    implementation("com.squareup.retrofit2:converter-gson:2.9.0") //convierte el array de json a un objeto
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")
    implementation("com.squareup.retrofit2:converter-scalars:2.9.0")

    //View model y live data
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    //KTX
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")

    //Glide
    implementation("com.github.bumptech.glide:glide:4.13.2")

    // Hilt
    implementation("com.google.dagger:hilt-android:$hilt_version")
    kapt("com.google.dagger:hilt-compiler:$hilt_version")
    annotationProcessor("com.google.dagger:hilt-compiler:$hilt_version")

    // Room
    implementation("androidx.room:room-ktx:$room_version")
    kapt("androidx.room:room-compiler:$room_version")

    //Glide
    implementation("com.github.bumptech.glide:glide:4.16.0")




}