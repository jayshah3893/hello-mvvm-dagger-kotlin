package com.paijorx.hello_mvvm_dagger2_kotlin.di.module

import android.app.Application
import androidx.room.Room
import com.paijorx.hello_mvvm_dagger2_kotlin.dao.CryptoCurrencyDao
import com.paijorx.hello_mvvm_dagger2_kotlin.db.Database
import com.paijorx.hello_mvvm_dagger2_kotlin.utils.Constants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabase(application: Application): Database = Room.databaseBuilder(application,
        Database::class.java, Constants.DATABASE_NAME).build()

    @Provides
    @Singleton
    fun providesCryptoCurrencyDao(database: Database): CryptoCurrencyDao = database.cryptoCurrenciesDao()
}