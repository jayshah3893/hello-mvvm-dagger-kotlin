package com.paijorx.hello_mvvm_dagger2_kotlin.di.module

import com.paijorx.hello_mvvm_dagger2_kotlin.ui.CryptoCurrenciesActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeCryptoCurrenciesActivity(): CryptoCurrenciesActivity
}