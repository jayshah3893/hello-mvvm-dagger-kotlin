package com.paijorx.hello_mvvm_dagger2_kotlin.di.component

import android.app.Application
import com.paijorx.hello_mvvm_dagger2_kotlin.CryptoCurrencyApplication
import com.paijorx.hello_mvvm_dagger2_kotlin.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ApplicationModule::class,
        DatabaseModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        ActivityModule::class
    ])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun injectLo(application: CryptoCurrencyApplication)
}