package com.example.onibussp.repository

import com.example.onibussp.model.Linhas
import com.example.onibussp.model.Paradas
import com.google.gson.JsonArray
import com.google.gson.JsonObject

sealed class RepositoryStatus{
    class SucessoLinhas(val response : List<Linhas>) : RepositoryStatus()
    class SucessoAuthentication(val response : Boolean) : RepositoryStatus()
    class Erro(val error : Throwable) : RepositoryStatus()
    class SucessoParadas(val response : List<Paradas>) : RepositoryStatus()
}

interface Repository {
    suspend fun getAuthentication() : RepositoryStatus
    suspend fun getLines(linha : String) : RepositoryStatus
    suspend fun getParadasPorLinhas(cdLinha : String) : RepositoryStatus
}