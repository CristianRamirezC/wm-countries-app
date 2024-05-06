package com.wimobile.wmcountriesapp.core.di

import android.content.Context
import androidx.room.Room
import com.wimobile.wmcountriesapp.BuildConfig
import com.wimobile.wmcountriesapp.data.database.CountriesDatabase
import com.wimobile.wmcountriesapp.data.database.dao.CountriesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideRoom(@ApplicationContext context: Context): CountriesDatabase =
        Room.databaseBuilder(context, CountriesDatabase::class.java, BuildConfig.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideCountriesDao(db: CountriesDatabase): CountriesDao = db.getCountriesDao()
}
