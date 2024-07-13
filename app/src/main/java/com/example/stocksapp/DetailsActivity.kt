package com.example.stocksapp

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class DetailsActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val tickerTextView = findViewById<TextView>(R.id.text)

        // Retrieve ticker from intent extras
        val ticker = intent.getStringExtra("ticker")

        // Display ticker in TextView or wherever needed
//        tickerTextView.text = ticker



    }
}
