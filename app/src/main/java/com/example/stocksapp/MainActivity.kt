package com.example.stocksapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tab_layout = findViewById<TabLayout>(R.id.tab_layout)
        val view_pager = findViewById<ViewPager2>(R.id.view_pager)


        setSupportActionBar(findViewById<Toolbar>(R.id.toolbar))



        // Set up the ViewPager2 with an inline FragmentStateAdapter
        view_pager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = 2

            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> FragmentOne()
                    else -> FragmentTwo()
                }
            }
        }

        // Set up the TabLayout with the ViewPager2
        TabLayoutMediator(tab_layout, view_pager) { tab, position ->
            tab.text = when (position) {
                0 -> "TOP GAINERS"
                else -> "TOP LOSERS"
            }
        }.attach()
    }
}