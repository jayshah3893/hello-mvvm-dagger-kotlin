package com.paijorx.hello_mvvm_dagger2_kotlin

import android.app.Activity
import android.app.Application
import com.paijorx.hello_mvvm_dagger2_kotlin.di.component.DaggerApplicationComponent
import com.paijorx.hello_mvvm_dagger2_kotlin.di.module.*
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class CryptoCurrencyApplication: Application(), HasActivityInjector {

    @Inject lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .databaseModule(DatabaseModule())
            .networkModule(NetworkModule(BuildConfig.URL))
            .repositoryModule(RepositoryModule())
            .viewModelModule(ViewModelModule())
            .build()
            .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

}