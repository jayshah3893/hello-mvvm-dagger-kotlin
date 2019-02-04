package com.paijorx.hello_mvvm_dagger2_kotlin.di.component

import android.app.Application
import com.paijorx.hello_mvvm_dagger2_kotlin.di.module.ApplicationModule
import com.paijorx.hello_mvvm_dagger2_kotlin.di.module.BuildersModule
import com.paijorx.hello_mvvm_dagger2_kotlin.di.module.DatabaseModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        BuildersModule::class,
        ApplicationModule::class,
        DatabaseModule::class
    ])
interface ApplicationComponent {
    fun inject(application: Application)
}