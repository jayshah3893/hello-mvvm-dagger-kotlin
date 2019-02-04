package com.paijorx.hello_mvvm_dagger2_kotlin.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.paijorx.hello_mvvm_dagger2_kotlin.dao.CryptoCurrenciesDao
import com.paijorx.hello_mvvm_dagger2_kotlin.model.CryptoCurrency

@Database(entities = [CryptoCurrency::class], version = 1)
abstract class Database :  RoomDatabase() {
    abstract fun cryptoCurrenciesDao(): CryptoCurrenciesDao
}