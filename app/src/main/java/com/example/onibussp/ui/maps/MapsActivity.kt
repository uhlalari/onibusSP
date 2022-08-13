package com.example.onibussp.ui.maps

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.toBitmap
import com.example.onibussp.R
import com.example.onibussp.model.Paradas
import com.example.onibussp.model.Posicao
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity() {
    private val viewModel: MapsViewModel = MapsViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val cdLinha = intent.extras?.get("cdLinha").toString()
        val mapFragment =
            supportFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment

        viewModel.getParadasPorLinhas(cdLinha)
        viewModel.getPosicao(cdLinha)


        viewModel.paradas.observe(this) {
            viewModel.posicao.observe(this) { posicao ->
                mapFragment.getMapAsync { googleMap ->
                    addMarkers(googleMap, it, posicao)

                    googleMap.setOnMapLoadedCallback {
                        val bounds = LatLngBounds.builder()

                        it.forEach {
                            bounds.include(LatLng(it.py, it.px))
                        }

                        googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(),
                            100))
                    }
                }
            }
        }
    }

    private fun addMarkers(googleMap: GoogleMap, places: List<Paradas>, onibus: Posicao) {
        val bitmap = AppCompatResources.getDrawable(this, R.drawable.ic_bus_24)?.toBitmap()
        val bitmapRed = AppCompatResources.getDrawable(this, R.drawable.ic_bus_red_24)?.toBitmap()
        places.forEach {
            val marker = googleMap.addMarker(
                MarkerOptions()
                    .title(it.np)
                    .snippet(it.ed)
                    .position(LatLng(it.py, it.px))
                    .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
            )
        }

        onibus.vs.forEach {
            val marker = googleMap.addMarker(
                MarkerOptions()
                    .title("Onibus")
                    .position(LatLng(it.py, it.px))
                    .icon(BitmapDescriptorFactory.fromBitmap(bitmapRed))
            )
        }
    }

}