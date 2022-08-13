package com.example.onibussp.repository

import com.example.onibussp.model.Linhas
import com.example.onibussp.model.Paradas
import com.example.onibussp.model.Posicao

sealed class RepositoryStatus {
    class SucessoLinhas(val response: List<Linhas>) : RepositoryStatus()
    class SucessoAuthentication(val response: Boolean) : RepositoryStatus()
    class Erro(val error: Throwable) : RepositoryStatus()
    class SucessoParadas(val response: List<Paradas>) : RepositoryStatus()
    class SucessoPosicao(val response: Posicao) : RepositoryStatus()
}

interface Repository {
    suspend fun getAuthentication(): RepositoryStatus
    suspend fun getLines(linha: String): RepositoryStatus
    suspend fun getParadasPorLinhas(cdLinha: String): RepositoryStatus
    suspend fun getPosicao(cdLinha: String): RepositoryStatus

}