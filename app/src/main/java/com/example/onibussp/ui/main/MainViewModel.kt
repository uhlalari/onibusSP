package com.example.onibussp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onibussp.model.Linhas
import com.example.onibussp.repository.Repository
import com.example.onibussp.repository.RepositoryImpl
import com.example.onibussp.repository.RepositoryStatus
import kotlinx.coroutines.launch

class MainViewModel (private val repository: Repository = RepositoryImpl()): ViewModel() {
    private val _lines = MutableLiveData<List<Linhas>>()
    val lines : LiveData<List<Linhas>>
        get() = _lines

    private val _authentication = MutableLiveData<Boolean>()
    val authentication : LiveData<Boolean>
        get() = _authentication

    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable>
        get() = _error

    fun getAuthentication() = viewModelScope.launch{

        repository.getAuthentication().apply {
            when(this){
                is RepositoryStatus.SucessoAuthentication-> _authentication.value = response
                is RepositoryStatus.Erro -> _error.value = error
                else -> {}
            }
        }
    }

    fun getLines(linha : String) = viewModelScope.launch{

        repository.getLines(linha).apply {
            when(this){
                is RepositoryStatus.SucessoLinhas-> _lines.value = response
                is RepositoryStatus.Erro -> _error.value = error
                else -> {}
            }
        }
    }
}