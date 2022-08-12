package com.example.onibussp.repository

import com.example.onibussp.model.Linhas
import com.google.gson.JsonArray
import com.google.gson.JsonObject

sealed class RepositoryStatus{
    class SucessoLinhas(val response : List<Linhas>) : RepositoryStatus()
    class SucessoAuthentication(val response : Boolean) : RepositoryStatus()
    class Erro(val error : Throwable) : RepositoryStatus()
}

interface Repository {
    suspend fun getAuthentication() : RepositoryStatus
    suspend fun getLines(linha : String) : RepositoryStatus
}