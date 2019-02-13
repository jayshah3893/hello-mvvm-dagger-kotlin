package com.paijorx.hello_mvvm_dagger2_kotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paijorx.hello_mvvm_dagger2_kotlin.model.CryptoCurrency
import com.paijorx.hello_mvvm_dagger2_kotlin.repository.CryptoCurrencyRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CryptoCurrencyViewModel @Inject constructor(private val cryptoCurrencyRepository: CryptoCurrencyRepository): ViewModel() {

    var result: MutableLiveData<List<CryptoCurrency>> = MutableLiveData()
    var error: MutableLiveData<String> = MutableLiveData()
    var loader: MutableLiveData<Boolean> = MutableLiveData()

    private lateinit var disposableObserver: DisposableObserver<List<CryptoCurrency>>

    fun getResult(): LiveData<List<CryptoCurrency>> = result

    fun getError(): LiveData<String> = error

    fun getLoader(): LiveData<Boolean> = loader

    fun loadCryptoCurrencies(limit: Int, offset: Int) {
        disposableObserver = object : DisposableObserver<List<CryptoCurrency>>() {
            override fun onComplete() {

            }

            override fun onNext(cryptoCurrencies: List<CryptoCurrency>) {
                result.postValue(cryptoCurrencies)
                loader.postValue(false)
            }

            override fun onError(e: Throwable) {
                error.postValue(e.message)
                loader.postValue(false)
            }

        }

        cryptoCurrencyRepository.getCryptoCurrencies(limit, offset)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .debounce(400, TimeUnit.MILLISECONDS)
            .subscribe(disposableObserver)
    }

    fun disposeElements() {
        disposableObserver?.let {
            if (it.isDisposed) disposableObserver.dispose()
        }
    }
}