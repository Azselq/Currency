package com.example.currency.adapter

import Valute
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.currency.databinding.ItemCurrencyBinding
import kotlin.math.roundToInt

class CurrencyAdapter(private val valutes: HashMap<String, Valute>, number: Int): RecyclerView.Adapter<CurrencyAdapter.CurrencyHolder>(){
    class CurrencyHolder(val binding: ItemCurrencyBinding): RecyclerView.ViewHolder(binding.root)
    val currentNumber = number

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyHolder {
        val view = LayoutInflater.from(parent.context)
        val binding = ItemCurrencyBinding.inflate(view,parent,false)
        return CurrencyHolder(binding)
    }

    override fun onBindViewHolder(holder: CurrencyHolder, position: Int) {
        val currency = valutes.values.elementAt(position)
        with(holder.binding){
            tvCurrency.text = currency.name
            tvCost.text = currency.value.toString()
            tvFinal.text = (( (currentNumber) * currency.value).roundToInt()).toString()
        }
    }

    override fun getItemCount(): Int {
        return valutes.size
    }
}