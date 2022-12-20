package com.example.currency.rest

import CurrencyBase
import retrofit2.Call
import retrofit2.http.GET

interface CurrencyApi {
    @GET("daily_json.js")
    fun getResult(): Call<CurrencyBase>
}