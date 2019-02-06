package com.paijorx.hello_mvvm_dagger2_kotlin.di.module

import android.app.Application
import androidx.room.Room
import com.paijorx.hello_mvvm_dagger2_kotlin.dao.CryptoCurrencyDao
import com.paijorx.hello_mvvm_dagger2_kotlin.db.Database
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabase(application: Application): Database = Room.databaseBuilder(application,
        Database::class.java,"CryptoCurrency.db").build()

    @Provides
    @Singleton
    fun providesCryptoCurrenciesDao(database: Database): CryptoCurrencyDao = database.cryptoCurrenciesDao()
}