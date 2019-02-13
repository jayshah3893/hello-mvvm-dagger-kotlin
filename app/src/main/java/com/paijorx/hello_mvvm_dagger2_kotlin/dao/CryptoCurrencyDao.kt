package com.paijorx.hello_mvvm_dagger2_kotlin.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.paijorx.hello_mvvm_dagger2_kotlin.model.CryptoCurrency
import io.reactivex.Single

@Dao
interface CryptoCurrencyDao {
    @Query("select * from tCryptoCurrency order by rank limit :limit offset :offset")
    fun select(limit: Int, offset: Int): Single<List<CryptoCurrency>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cryptoCurrency: CryptoCurrency)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cryptoCurrencies: List<CryptoCurrency>)
}