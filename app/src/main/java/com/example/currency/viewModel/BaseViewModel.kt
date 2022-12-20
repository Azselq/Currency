package com.example.currency.viewModel

import CurrencyBase
import Valute
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.currency.databinding.FragmentBaseBinding
import com.example.currency.rest.CurrencyRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BaseViewModel : ViewModel() {
    val repository = CurrencyRepository()
    val currencyLiveData = MutableLiveData<HashMap<String, Valute>>()
    private val currencyList = mutableListOf<Valute>()

    fun printInformation(){
        repository.getCurrency().enqueue(object : Callback<CurrencyBase> {
            override fun onResponse(call: Call<CurrencyBase>, response: Response<CurrencyBase>) {
                val currentResponse = response.body()
                if (currentResponse != null) {
                    currencyLiveData.value = currentResponse.valute
                    Log.d("test", "${currencyLiveData}")
                } else {
                    Log.d("test", "Пустой")

                }
            }

            override fun onFailure(call: Call<CurrencyBase>, t: Throwable) {
                Log.d("test", "Ошибка ", t)
            }
        })
    }
}