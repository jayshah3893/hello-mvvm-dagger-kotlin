package com.paijorx.hello_mvvm_dagger2_kotlin.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.paijorx.hello_mvvm_dagger2_kotlin.di.scope.ViewModelKey
import com.paijorx.hello_mvvm_dagger2_kotlin.viewmodel.CryptoCurrencyViewModel
import com.paijorx.hello_mvvm_dagger2_kotlin.viewmodel.CryptoCurrencyViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(CryptoCurrencyViewModel::class)
    abstract fun bindCryptoCurrencyViewModel(cryptoCurrencyViewModel: CryptoCurrencyViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: CryptoCurrencyViewModelFactory) : ViewModelProvider.Factory
}