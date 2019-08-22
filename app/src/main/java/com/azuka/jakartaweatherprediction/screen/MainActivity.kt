package com.azuka.jakartaweatherprediction.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.azuka.jakartaweatherprediction.R
import com.azuka.jakartaweatherprediction.databinding.ActivityMainBinding
import com.azuka.jakartaweatherprediction.formatToDate
import java.util.*

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val adapter = HourlyWeatherAdapter()

        binding.hourly.adapter = adapter

        viewModel.hourlyWeatherToday.observe(this, Observer { infoList ->
            infoList?.let {
                adapter.submitList(infoList)
            }
        })
    }
}
