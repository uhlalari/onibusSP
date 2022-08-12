package com.example.onibussp.api

import com.example.onibussp.api.Const.API_URL
import com.example.onibussp.api.Const.TOKEN
import com.example.onibussp.model.Linhas
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Endpoint {
    @POST("Login/Autenticar?token=${TOKEN}")
    suspend fun getAuthentication() : Boolean

    @GET("/Linha/Buscar")
    suspend fun getLines(@Query("termosBusca") linha: String) : JsonArray

    companion object {
        val endpoint: Endpoint by lazy {
            val endpoint = Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            endpoint.create(Endpoint::class.java)
        }
    }
}