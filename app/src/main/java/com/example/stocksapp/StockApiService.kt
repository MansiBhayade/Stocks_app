package com.example.stocksapp

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface StockApiService {
    @GET("query?function=TOP_GAINERS_LOSERS&apikey=C5X7MVXVTV2A0NXD")
    fun getTopGainers(): Call<StockDataResponse>

    @GET("query?function=TOP_GAINERS_LOSERS&apikey=C5X7MVXVTV2A0NXD")
    fun getTopLosers(): Call<StockDataResponse>


    @GET("query?function=OVERVIEW&apikey=C5X7MVXVTV2A0NXD")
    fun getStockOverview(
        @Query("symbol") symbol: String
    ): Call<StockOverviewResponse>

    @GET("query?function=TIME_SERIES_DAILY&apikey=C5X7MVXVTV2A0NXD")
    fun getDailyPrices(
        @Query("symbol") symbol: String
    ): Call<JsonObject>
}
