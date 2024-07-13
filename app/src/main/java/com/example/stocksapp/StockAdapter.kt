package com.example.stocksapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StockAdapter(private val stocks: List<Stock>) : RecyclerView.Adapter<StockAdapter.StockViewHolder>() {

    class StockViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ticker: TextView = itemView.findViewById(R.id.ticker)
        val price: TextView = itemView.findViewById(R.id.price)
        val changePercentage: TextView = itemView.findViewById(R.id.change_percentage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_stock, parent, false)
        return StockViewHolder(view)
    }

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        val stock = stocks[position]
        holder.ticker.text = stock.ticker
        holder.price.text = "$${stock.price}"
        holder.changePercentage.text = stock.change_percentage
    }

    override fun getItemCount(): Int = stocks.size
}
