package com.paijorx.hello_mvvm_dagger2_kotlin.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.paijorx.hello_mvvm_dagger2_kotlin.R
import com.paijorx.hello_mvvm_dagger2_kotlin.model.CryptoCurrency
import com.paijorx.hello_mvvm_dagger2_kotlin.network.ApiClient
import com.paijorx.hello_mvvm_dagger2_kotlin.network.ApiInterface
import dagger.android.AndroidInjection
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class CryptoCurrenciesActivity: AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()

    companion object {
        private val TAG = CryptoCurrenciesActivity::class.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crypto_currencies)

        showCryptoCurrencies()
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }

    private fun showCryptoCurrencies() {
        val response = getCryptoCurrencies()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<List<CryptoCurrency>>() {
                override fun onComplete() {
                    Log.d(TAG, "complete")
                }

                override fun onNext(cryptocurrencies: List<CryptoCurrency>) {
                    Log.d(TAG, "${cryptocurrencies.size}")
                }

                override fun onError(e: Throwable) {
                    Log.e(TAG, e.message)
                }

            })
    }

    private fun getCryptoCurrencies(): Observable<List<CryptoCurrency>> {
        val retrofit = ApiClient.getClient()
        val apiInterface = retrofit.create(ApiInterface::class.java)

        return apiInterface.getCryptocurrencies("0")
    }
}