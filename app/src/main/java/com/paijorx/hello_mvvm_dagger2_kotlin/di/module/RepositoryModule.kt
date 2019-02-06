package com.paijorx.hello_mvvm_dagger2_kotlin.di.module

import com.paijorx.hello_mvvm_dagger2_kotlin.dao.CryptoCurrencyDao
import com.paijorx.hello_mvvm_dagger2_kotlin.network.ApiInterface
import com.paijorx.hello_mvvm_dagger2_kotlin.repository.CryptoCurrencyRepository
import com.paijorx.hello_mvvm_dagger2_kotlin.utils.Utils
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun providesCryptoCurrencyRepository(apiInterface: ApiInterface,
                                         cryptoCurrencyDao: CryptoCurrencyDao,
                                         utils: Utils
    ): CryptoCurrencyRepository = CryptoCurrencyRepository(apiInterface, cryptoCurrencyDao, utils)
}