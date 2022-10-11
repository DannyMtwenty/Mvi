package com.example.mvitest.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvitest.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showMainFragment()
    }

    //infrate main fragment
    fun showMainFragment() {
        supportFragmentManager
           .beginTransaction()
        .replace(R.id.fragment_container, MainFragment(),"main_fragment")
          .commit()
    }
}