package com.example.onibussp.repository

import android.util.Log
import com.example.onibussp.api.Endpoint.Companion.endpoint
import com.example.onibussp.model.Linhas
import com.example.onibussp.model.Paradas
import com.example.onibussp.model.Posicao
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepositoryImpl : Repository {

    val gson = GsonBuilder().create()

    override suspend fun getAuthentication(): RepositoryStatus {

        return withContext(Dispatchers.IO) {
            try {
                val response = endpoint.getAuthentication()
                RepositoryStatus.SucessoAuthentication(response)
            } catch (t: Throwable) {
                //Mock temporário enquanto existe problemas na API que retorna o erro timeout
                if (t.message == "timeout") {
                    RepositoryStatus.SucessoAuthentication(true)
                } else {
                    RepositoryStatus.Erro(t)
                }
            }
        }
    }

    override suspend fun getLines(linha: String): RepositoryStatus {

        return withContext(Dispatchers.IO) {
            try {
                val response = endpoint.getLines(linha)
                val lista = gson.fromJson(response, Array<Linhas>::class.java).toList()
                RepositoryStatus.SucessoLinhas(lista)
            } catch (t: Throwable) {
                //Mock temporário enquanto existe problemas na API que retorna o erro timeout
                if (t.message == "timeout") {
                    val listaMok = listOf(
                        Linhas(1273, false, "8000", 1, 10, "Erro na API", "TERMINAL LAPA"),
                        Linhas(34041,
                            false,
                            "8000",
                            2,
                            10,
                            "PCA.RAMOS DE AZEVEDO",
                            "TERMINAL LAPA"),
                        Linhas(1273, false, "8000", 1, 10, "PCA.RAMOS DE AZEVEDO", "TERMINAL LAPA"),
                        Linhas(34041,
                            false,
                            "8000",
                            2,
                            10,
                            "PCA.RAMOS DE AZEVEDO",
                            "TERMINAL LAPA"),
                        Linhas(1273, false, "8000", 1, 10, "PCA.RAMOS DE AZEVEDO", "TERMINAL LAPA"),
                        Linhas(34041,
                            false,
                            "8000",
                            2,
                            10,
                            "PCA.RAMOS DE AZEVEDO",
                            "TERMINAL LAPA"),
                        Linhas(1273, false, "8000", 1, 10, "PCA.RAMOS DE AZEVEDO", "TERMINAL LAPA"),
                        Linhas(34041,
                            false,
                            "8000",
                            2,
                            10,
                            "PCA.RAMOS DE AZEVEDO",
                            "TERMINAL LAPA"),
                        Linhas(1273, false, "8000", 1, 10, "PCA.RAMOS DE AZEVEDO", "TERMINAL LAPA"),
                        Linhas(34041,
                            false,
                            "8000",
                            2,
                            10,
                            "PCA.RAMOS DE AZEVEDO",
                            "TERMINAL LAPA"),
                        Linhas(1273, false, "8000", 1, 10, "PCA.RAMOS DE AZEVEDO", "TERMINAL LAPA"),
                        Linhas(34041,
                            false,
                            "8000",
                            2,
                            10,
                            "PCA.RAMOS DE AZEVEDO",
                            "TERMINAL LAPA"),
                        Linhas(1273, false, "8000", 1, 10, "PCA.RAMOS DE AZEVEDO", "TERMINAL LAPA"),
                        Linhas(34041, false, "8000", 2, 10, "PCA.RAMOS DE AZEVEDO", "TERMINAL LAPA")
                    )
                    RepositoryStatus.SucessoLinhas(listaMok)
                } else {
                    RepositoryStatus.Erro(t)
                }
            }
        }
    }

    override suspend fun getParadasPorLinhas(cdLinha: String): RepositoryStatus {
        return withContext(Dispatchers.IO) {
            try {
                var response = endpoint.getParadasPorLinhas(cdLinha)
                val lista = gson.fromJson(response, Array<Paradas>::class.java).toList()
                RepositoryStatus.SucessoParadas(lista)
            } catch (t: Throwable) {
                RepositoryStatus.Erro(t)
            }
        }
    }

    override suspend fun getPosicao(cdLinha: String): RepositoryStatus {
        return withContext(Dispatchers.IO) {
            try {
                var response = endpoint.getPosicao(cdLinha)
                Log.v("teste", "" + response)
                var posicao = gson.fromJson(response, Posicao::class.java)
                RepositoryStatus.SucessoPosicao(posicao)

            } catch (t: Throwable) {
                RepositoryStatus.Erro(t)

            }
        }

    }
}