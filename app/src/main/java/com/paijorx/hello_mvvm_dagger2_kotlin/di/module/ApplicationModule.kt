package com.paijorx.hello_mvvm_dagger2_kotlin.di.module

import android.app.Application
import com.paijorx.hello_mvvm_dagger2_kotlin.utils.Utils
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Provides
    @Singleton
    fun provideUtils(application: Application): Utils = Utils(application)
}