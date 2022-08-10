package com.example.onibussp.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.onibussp.databinding.ActivityMainBinding
import com.example.onibussp.ui.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainAdapter: MainAdapter
    private val viewModel: MainViewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getAuthentication()

        viewModel.lines.observe(this) {
            mainAdapter = MainAdapter(it)
            binding.rvMain.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)
            binding.rvMain.adapter = mainAdapter

        }

        viewModel.authentication.observe(this) {
            if (it) {
                viewModel.getLines()
            }
        }

        viewModel.error.observe(this) {

        }
    }

}