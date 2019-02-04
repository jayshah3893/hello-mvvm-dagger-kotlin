package com.paijorx.hello_mvvm_dagger2_kotlin.network

import com.paijorx.hello_mvvm_dagger2_kotlin.model.CryptoCurrency
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("ticker/")
    fun getCryptocurrencies(@Query("start") start: String): Observable<List<CryptoCurrency>>
}