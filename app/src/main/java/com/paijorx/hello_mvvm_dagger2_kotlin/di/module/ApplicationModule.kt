package com.paijorx.hello_mvvm_dagger2_kotlin.di.module

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.paijorx.hello_mvvm_dagger2_kotlin.utils.Utils
import com.paijorx.hello_mvvm_dagger2_kotlin.viewmodel.CryptoCurrencyViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(val application: Application) {

    @Provides
    @Singleton
    fun providesApplication(): Application = application

    @Provides
    @Singleton
    fun providesUtils(): Utils = Utils(application)
}