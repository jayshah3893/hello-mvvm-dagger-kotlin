package com.paijorx.hello_mvvm_dagger2_kotlin.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.paijorx.hello_mvvm_dagger2_kotlin.dao.CryptoCurrencyDao
import com.paijorx.hello_mvvm_dagger2_kotlin.model.CryptoCurrency
import com.paijorx.hello_mvvm_dagger2_kotlin.utils.Constants

@Database(entities = [CryptoCurrency::class], version = Constants.DATABASE_VERSION)
abstract class Database :  RoomDatabase() {
    abstract fun cryptoCurrenciesDao(): CryptoCurrencyDao
}