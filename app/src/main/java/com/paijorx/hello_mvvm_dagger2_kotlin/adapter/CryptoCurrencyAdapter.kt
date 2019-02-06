package com.paijorx.hello_mvvm_dagger2_kotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.paijorx.hello_mvvm_dagger2_kotlin.R
import com.paijorx.hello_mvvm_dagger2_kotlin.model.CryptoCurrency

class CryptoCurrencyAdapter constructor(cryptoCurrencies: List<CryptoCurrency>?): RecyclerView.Adapter<CryptoCurrencyAdapter.CryptoCurrencyViewHolder>() {

    private var cryptoCurrencies = ArrayList<CryptoCurrency>()

    init {
        this.cryptoCurrencies = cryptoCurrencies as ArrayList<CryptoCurrency>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoCurrencyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.cryptocurrency_list_item, parent, false)

        return CryptoCurrencyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return cryptoCurrencies.size
    }

    override fun onBindViewHolder(holder: CryptoCurrencyViewHolder, position: Int) {
        val cryptoCurrency = cryptoCurrencies[position]
        holder.bind(cryptoCurrency)
    }

    class CryptoCurrencyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var id = itemView.findViewById<TextView>(R.id.cryptocurrency_id)

        fun bind(cryptoCurrency: CryptoCurrency) {
            id.text = cryptoCurrency.id
        }
    }

    fun add(cryptoCurrencies: List<CryptoCurrency>) {
        val initPosition = this.cryptoCurrencies.size
        this.cryptoCurrencies.addAll(cryptoCurrencies)
        notifyItemRangeInserted(initPosition, this.cryptoCurrencies.size)
    }
}