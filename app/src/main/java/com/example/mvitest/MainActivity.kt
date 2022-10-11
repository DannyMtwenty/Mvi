package com.example.mvitest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvitest.ui.MainFragment

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