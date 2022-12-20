package com.example.currency.rest

import CurrencyBase
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CurrencyRepository {
    private val currencyApi: CurrencyApi

    init {
        val retrofit = Retrofit
            .Builder()
            .baseUrl("https://www.cbr-xml-daily.ru/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        currencyApi = retrofit.create(CurrencyApi::class.java)
    }


    fun getCurrency(): Call<CurrencyBase> {
        return currencyApi.getResult()
    }
}