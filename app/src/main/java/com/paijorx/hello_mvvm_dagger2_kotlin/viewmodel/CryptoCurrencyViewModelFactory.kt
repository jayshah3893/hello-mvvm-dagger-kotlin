package com.paijorx.hello_mvvm_dagger2_kotlin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException
import kotlin.reflect.jvm.internal.impl.javax.inject.Inject

class CryptoCurrencyViewModelFactory @Inject constructor(private val cryptoCurrencyViewModel: CryptoCurrencyViewModel) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CryptoCurrencyViewModel::class.java)) {
            return cryptoCurrencyViewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}