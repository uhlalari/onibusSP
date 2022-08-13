package com.example.onibussp.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.onibussp.databinding.ActivityMainBinding
import com.example.onibussp.ui.maps.MapsActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainAdapter: MainAdapter
    private val viewModel: MainViewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.searchMain.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if (currentFocus != null) {
                    val inputMethodManager: InputMethodManager =
                        getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
                }
                viewModel.getAuthentication()
            }
            false
        }

        viewModel.lines.observe(this) {
            mainAdapter = MainAdapter(it)
            binding.rvMain.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            binding.rvMain.adapter = mainAdapter

            mainAdapter.openLinhasDetails {
                val intent = Intent(this, MapsActivity::class.java)
                    .putExtra("cdLinha", it.cl.toString())
                startActivity(intent)
            }

            binding.rvMain.visibility = View.VISIBLE
            binding.tvMain.visibility = View.INVISIBLE

        }

        viewModel.authentication.observe(this) {
            if (it) {
                viewModel.getLines(binding.searchMain.text.toString())
            }
        }

        viewModel.error.observe(this) {
            Log.v("teste", "" + it)
        }
    }

}