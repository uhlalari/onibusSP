package com.example.onibussp.ui.maps

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onibussp.model.Paradas
import com.example.onibussp.model.Posicao
import com.example.onibussp.repository.Repository
import com.example.onibussp.repository.RepositoryImpl
import com.example.onibussp.repository.RepositoryStatus
import kotlinx.coroutines.launch

class MapsViewModel  (private val repository: Repository = RepositoryImpl()): ViewModel() {
    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable>
        get() = _error

    private val _paradas = MutableLiveData<List<Paradas>>()
    val paradas: LiveData<List<Paradas>>
        get() = _paradas

    private val _posicao = MutableLiveData<Posicao>()
    val posicao: LiveData<Posicao>
        get() = _posicao

    fun getParadasPorLinhas(cdLinha : String) = viewModelScope.launch{
        repository.getParadasPorLinhas(cdLinha) .apply {
            when(this){
                is RepositoryStatus.SucessoParadas-> _paradas.value = response
                is RepositoryStatus.Erro -> _error.value = error
                else -> {}
            }
        }
    }
    fun getPosicao(cdLinha : String) = viewModelScope.launch{
        repository.getPosicao(cdLinha) .apply {
            when(this){
                is RepositoryStatus.SucessoPosicao-> _posicao.value = response
                is RepositoryStatus.Erro -> _error.value = error
                else -> {}
            }
        }
    }

}