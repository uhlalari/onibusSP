package com.example.onibussp.ui.maps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.onibussp.R
import com.google.android.gms.maps.SupportMapFragment

class ActivityMaps : AppCompatActivity() {
    private val viewModel : MapsViewModel = MapsViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val cdLinha = intent.extras?.get("cdLinha").toString()

        Log.v("teste", cdLinha)
        viewModel.getParadasPorLinhas(cdLinha)

        viewModel.paradas.observe(this){
            Log.v("teste", "Retorno - "+it)
        }
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
    }
}