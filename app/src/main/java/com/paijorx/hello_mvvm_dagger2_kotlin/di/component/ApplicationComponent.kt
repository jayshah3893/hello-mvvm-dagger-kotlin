package com.paijorx.hello_mvvm_dagger2_kotlin.di.component

import android.app.Application
import com.paijorx.hello_mvvm_dagger2_kotlin.CryptoCurrencyApplication
import com.paijorx.hello_mvvm_dagger2_kotlin.di.module.*
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        BuilderModule::class,
        ApplicationModule::class,
        DatabaseModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        ViewModelModule::class
    ])
interface ApplicationComponent {
    fun inject(application: CryptoCurrencyApplication)
}