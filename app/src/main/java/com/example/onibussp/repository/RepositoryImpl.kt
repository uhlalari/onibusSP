package com.example.onibussp.repository

import android.util.Log
import com.example.onibussp.api.Endpoint.Companion.endpoint
import com.example.onibussp.model.Linhas
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepositoryImpl : Repository {

    val gson = GsonBuilder().create()

    override suspend fun getAuthentication(): RepositoryStatus {

        return withContext(Dispatchers.IO){
            try {
                val response= endpoint.getAuthentication()
                RepositoryStatus.SucessoAuthentication(response)
            }catch (t : Throwable){
                RepositoryStatus.Erro(t)
            }
        }
    }

    override suspend fun getLines(): RepositoryStatus {

        return withContext(Dispatchers.IO){
            try {
                val response = endpoint.getLines()
                val lista = gson.fromJson(response, Array<Linhas>::class.java).toList()
                RepositoryStatus.SucessoLinhas(lista)
            }catch (t : Throwable){
                RepositoryStatus.Erro(t)
            }
        }
    }
}