package com.example.stocksapp

import retrofit2.Call
import retrofit2.http.GET

interface StockApiService {
    @GET("query?function=TOP_GAINERS_LOSERS&apikey=C5X7MVXVTV2A0NXD")
    fun getTopGainers(): Call<StockDataResponse>
}
