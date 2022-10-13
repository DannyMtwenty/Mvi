package com.example.mvitest.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.mvitest.R

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
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