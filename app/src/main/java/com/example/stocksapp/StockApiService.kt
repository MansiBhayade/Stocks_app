package com.example.stocksapp

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface StockApiService {
    @GET("query?function=TOP_GAINERS_LOSERS&apikey=9P4TR7UBRXM7ILL5")
    fun getTopGainers(): Call<StockDataResponse>

    @GET("query?function=TOP_GAINERS_LOSERS&apikey=9P4TR7UBRXM7ILL5")
    fun getTopLosers(): Call<StockDataResponse>


    @GET("query?function=OVERVIEW&apikey=9P4TR7UBRXM7ILL5")
    fun getStockOverview(
        @Query("symbol") symbol: String
    ): Call<StockOverviewResponse>

    @GET("query?function=TIME_SERIES_DAILY&apikey=9P4TR7UBRXM7ILL5")
    fun getDailyPrices(
        @Query("symbol") symbol: String
    ): Call<JsonObject>
}
