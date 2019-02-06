package com.paijorx.hello_mvvm_dagger2_kotlin.di.module

import androidx.lifecycle.ViewModelProvider
import com.paijorx.hello_mvvm_dagger2_kotlin.repository.CryptoCurrencyRepository
import com.paijorx.hello_mvvm_dagger2_kotlin.viewmodel.CryptoCurrencyViewModel
import com.paijorx.hello_mvvm_dagger2_kotlin.viewmodel.CryptoCurrencyViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelModule {
    @Provides
    @Singleton
    fun providesCryptoCurrencyViewModel(cryptoCurrencyRepository: CryptoCurrencyRepository): CryptoCurrencyViewModel =
        CryptoCurrencyViewModel(cryptoCurrencyRepository)

    @Provides
    @Singleton
    fun providesCryptoCurrencyViewModelFactory(cryptoCurrencyViewModel: CryptoCurrencyViewModel): CryptoCurrencyViewModelFactory =
        CryptoCurrencyViewModelFactory(cryptoCurrencyViewModel)

    @Provides
    fun providesViewModelFactory(cryptoCurrencyViewModelFactory: CryptoCurrencyViewModelFactory): ViewModelProvider.Factory = cryptoCurrencyViewModelFactory

}