package com.example.onibussp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.onibussp.R

class MainActivity : AppCompatActivity() {

    private val viewModel : MainViewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getAuthentication()
        viewModel.lines.observe(this){
            Log.v("teste", ""+it)
        }

        viewModel.authentication.observe(this){
            Log.v("teste",""+it)
            if (it){
                viewModel.getLines()
            }
        }

        viewModel.error.observe(this){
            Log.v("teste", ""+it)
        }
    }
}