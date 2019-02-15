package com.paijorx.hello_mvvm_dagger2_kotlin.ui.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.paijorx.hello_mvvm_dagger2_kotlin.R
import com.paijorx.hello_mvvm_dagger2_kotlin.adapter.CryptoCurrencyAdapter
import com.paijorx.hello_mvvm_dagger2_kotlin.base.BaseFragment
import com.paijorx.hello_mvvm_dagger2_kotlin.databinding.FragmentCryptocurrencyBinding
import com.paijorx.hello_mvvm_dagger2_kotlin.model.CryptoCurrency
import com.paijorx.hello_mvvm_dagger2_kotlin.utils.Constants
import com.paijorx.hello_mvvm_dagger2_kotlin.viewmodel.CryptoCurrencyViewModel
import com.paijorx.hello_mvvm_dagger2_kotlin.viewmodel.CryptoCurrencyViewModelFactory
import com.paijorx.hello_mvvm_dagger2_kotlin.widget.InfiniteScrollListener
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_cryptocurrency.*
import javax.inject.Inject

class CryptoCurrencyFragment : Fragment() {

    @Inject lateinit var viewModelFactory: CryptoCurrencyViewModelFactory
    private lateinit var cryptoCurrencyViewModel: CryptoCurrencyViewModel

    private lateinit var binding: FragmentCryptocurrencyBinding

    private var cryptoCurrencyAdapter = CryptoCurrencyAdapter(ArrayList())
    private var currentPage = 0

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cryptocurrency, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cryptoCurrencyViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(CryptoCurrencyViewModel::class.java)

        initializeRecycler()

        loadData()
        observerResult()
        observerError()
        observerLoader()
    }

    override fun onDestroy() {
        cryptoCurrencyViewModel.disposeElements()
        super.onDestroy()
    }

    private fun initializeRecycler() {
        val gridLayoutManager = GridLayoutManager(requireContext(), 1)
        gridLayoutManager.orientation = RecyclerView.VERTICAL

        recycler.apply {
            setHasFixedSize(true)
            layoutManager = gridLayoutManager
            addOnScrollListener(InfiniteScrollListener({loadData()}, gridLayoutManager))
        }
    }


    private fun loadData() {
        progressBar.visibility  = View.VISIBLE
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
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun observerLoader() {
        cryptoCurrencyViewModel.getLoader().observe(this, Observer<Boolean> {
            progressBar.visibility = if (it) View.VISIBLE
            else View.GONE
        })
    }
}