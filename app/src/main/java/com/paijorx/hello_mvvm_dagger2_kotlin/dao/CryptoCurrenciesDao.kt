package com.paijorx.hello_mvvm_dagger2_kotlin.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.paijorx.hello_mvvm_dagger2_kotlin.model.CryptoCurrency

@Dao
interface CryptoCurrenciesDao {
    @Query("select * from tCryptoCurrency")
    fun select(): LiveData<List<CryptoCurrency>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cryptoCurrency: CryptoCurrency)
}