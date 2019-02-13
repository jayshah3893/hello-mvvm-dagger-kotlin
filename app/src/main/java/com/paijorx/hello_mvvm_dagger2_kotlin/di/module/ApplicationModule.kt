package com.paijorx.hello_mvvm_dagger2_kotlin.di.module

import android.app.Application
import com.paijorx.hello_mvvm_dagger2_kotlin.CryptoCurrencyApplication
import com.paijorx.hello_mvvm_dagger2_kotlin.utils.Utils
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class ApplicationModule {

    @Provides
    @Singleton
    fun providesApplication(application: CryptoCurrencyApplication) = application

    @Provides
    @Singleton
    fun providesUtils(application: CryptoCurrencyApplication): Utils = Utils(application)
}