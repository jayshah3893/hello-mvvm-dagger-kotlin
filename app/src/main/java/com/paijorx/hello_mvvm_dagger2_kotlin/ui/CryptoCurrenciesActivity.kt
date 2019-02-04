package com.paijorx.hello_mvvm_dagger2_kotlin.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.paijorx.hello_mvvm_dagger2_kotlin.R
import dagger.android.AndroidInjection

class CryptoCurrenciesActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crypto_currencies)
    }
}