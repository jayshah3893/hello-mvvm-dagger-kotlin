package com.paijorx.hello_mvvm_dagger2_kotlin.di.module

import com.paijorx.hello_mvvm_dagger2_kotlin.ui.list.CryptoCurrencyFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun provideCryptoCurrencyFragment(): CryptoCurrencyFragment
}