package com.paijorx.hello_mvvm_dagger2_kotlin.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.paijorx.hello_mvvm_dagger2_kotlin.R
import com.paijorx.hello_mvvm_dagger2_kotlin.adapter.CryptoCurrencyAdapter
import com.paijorx.hello_mvvm_dagger2_kotlin.db.Database
import com.paijorx.hello_mvvm_dagger2_kotlin.model.CryptoCurrency
import com.paijorx.hello_mvvm_dagger2_kotlin.utils.Constants
import com.paijorx.hello_mvvm_dagger2_kotlin.viewmodel.CryptoCurrencyViewModel
import com.paijorx.hello_mvvm_dagger2_kotlin.viewmodel.CryptoCurrencyViewModelFactory
import com.paijorx.hello_mvvm_dagger2_kotlin.widget.InfiniteScrollListener
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_cryptocurrency.*
import retrofit2.Retrofit
import javax.inject.Inject

class CryptoCurrencyActivity: AppCompatActivity() {

    @Inject lateinit var cryptoCurrencyViewModelFactory: CryptoCurrencyViewModelFactory
    @Inject lateinit var retrofit: Retrofit
    @Inject lateinit var database: Database
    private var cryptoCurrencyAdapter = CryptoCurrencyAdapter(ArrayList())
    private lateinit var cryptoCurrencyViewModel: CryptoCurrencyViewModel
    private var currentPage = 0

    companion object {
        private val TAG = CryptoCurrencyActivity::class.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cryptocurrency)

        val baseUrl = retrofit.baseUrl()
        Log.d(TAG, baseUrl.host())
        Log.d(TAG, database.isOpen.toString())

//
//        initializeRecycler()
//
//        cryptoCurrencyViewModel = ViewModelProviders.of(this, cryptoCurrencyViewModelFactory)
//            .get(CryptoCurrencyViewModel::class.java)
//
//        loadData()
//        observerResult()
//        observerError()
//        observerLoader()
    }

    override fun onDestroy() {
        cryptoCurrencyViewModel.disposeElements()
        super.onDestroy()
    }

    private fun initializeRecycler() {
        val gridLayoutManager = GridLayoutManager(this, 1)
        gridLayoutManager.orientation = RecyclerView.VERTICAL

        recycler.apply {
            setHasFixedSize(true)
            layoutManager = gridLayoutManager
            addOnScrollListener(InfiniteScrollListener({loadData()}, gridLayoutManager))
        }
    }

    private fun loadData() {
        progressBar.visibility = View.VISIBLE
        cryptoCurrencyViewModel.loadCryptoCurrencies(Constants.LIMIT, Constants.OFFSET)
        currentPage++
    }

    private fun observerResult() {
        cryptoCurrencyViewModel.getResult().observe(this, Observer<List<CryptoCurrency>> {
            it?.let {
                val position = cryptoCurrencyAdapter.itemCount
                cryptoCurrencyAdapter.add(it)
                recycler.adapter = cryptoCurrencyAdapter
                recycler.scrollToPosition(position - Constants.LIST_SCROLLING)
            }
        })
    }

    private fun observerError() {
        cryptoCurrencyViewModel.getError().observe(this, Observer<String> {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun observerLoader() {
        cryptoCurrencyViewModel.getLoader().observe(this, Observer<Boolean> {
            progressBar.visibility = if (it) View.VISIBLE
            else View.GONE
        })
    }
}