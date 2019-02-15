package com.paijorx.hello_mvvm_dagger2_kotlin.di.module

import com.paijorx.hello_mvvm_dagger2_kotlin.ui.CryptoCurrencyActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun provideCryptoCurrencyActivity(): CryptoCurrencyActivity
}