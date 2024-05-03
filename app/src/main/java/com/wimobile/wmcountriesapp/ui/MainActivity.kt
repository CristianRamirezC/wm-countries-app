package com.wimobile.wmcountriesapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wimobile.wmcountriesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

    }
}