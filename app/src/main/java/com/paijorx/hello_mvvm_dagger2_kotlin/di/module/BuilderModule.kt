package com.paijorx.hello_mvvm_dagger2_kotlin.di.module

import com.paijorx.hello_mvvm_dagger2_kotlin.ui.CryptoCurrencyActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuilderModule {
    @ContributesAndroidInjector
    abstract fun contributeCryptoCurrenciesActivity(): CryptoCurrencyActivity
}