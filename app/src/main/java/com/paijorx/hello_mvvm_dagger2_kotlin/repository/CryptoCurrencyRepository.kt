package com.paijorx.hello_mvvm_dagger2_kotlin.repository

import android.util.Log
import com.paijorx.hello_mvvm_dagger2_kotlin.dao.CryptoCurrencyDao
import com.paijorx.hello_mvvm_dagger2_kotlin.model.CryptoCurrency
import com.paijorx.hello_mvvm_dagger2_kotlin.network.ApiInterface
import com.paijorx.hello_mvvm_dagger2_kotlin.utils.Constants
import com.paijorx.hello_mvvm_dagger2_kotlin.utils.Utils
import io.reactivex.Observable
import javax.inject.Inject

class CryptoCurrencyRepository @Inject constructor(private val apiInterface: ApiInterface,
                                                   private val cryptoCurrencyDao: CryptoCurrencyDao,
                                                   private val utils: Utils) {

    companion object {
        private val TAG = CryptoCurrencyRepository::class.java.simpleName
    }

    fun getCryptoCurrencies(limit: Int, offset: Int): Observable<List<CryptoCurrency>> {
        val hasConnection = utils.isConnectedToInternet()

        val  observableFromApi = if (hasConnection) getCryptoCurrenciesFromApi()
        else null

        val observableFromDb = getCryptoCurrenciesFromDb(limit, offset)

        return if (hasConnection) Observable.concatArrayEager(observableFromApi, observableFromDb)
        else observableFromDb
    }

    private fun getCryptoCurrenciesFromApi(): Observable<List<CryptoCurrency>> {
        return apiInterface.getCryptCurrencies(Constants.START_ZERO_VALUE)
            .doOnNext {
                Log.d(TAG, "${it.size}")
                cryptoCurrencyDao.insert(it)
            }
    }

    private fun getCryptoCurrenciesFromDb(limit: Int, offset: Int): Observable<List<CryptoCurrency>> {
        return cryptoCurrencyDao.select(limit, offset)
            .toObservable()
            .doOnNext {
                Log.d(TAG, "${it.size}")
            }
    }
}