package com.paijorx.hello_mvvm_dagger2_kotlin.di.module

import android.app.Application
import androidx.room.Room
import com.paijorx.hello_mvvm_dagger2_kotlin.dao.CryptoCurrenciesDao
import com.paijorx.hello_mvvm_dagger2_kotlin.db.Database
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): Database = Room.databaseBuilder(application,
        Database::class.java,"CryptoCurrency.db").build()

    @Provides
    @Singleton
    fun provideCryptoCurrenciesDao(database: Database): CryptoCurrenciesDao = database.cryptoCurrenciesDao()
}