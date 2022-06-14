package com.example.marvelheroes.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelheroes.data.model.Character
import com.example.marvelheroes.data.model.Resource
import com.example.marvelheroes.data.repository.CharactersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(private val charactersRepository: CharactersRepository) : ViewModel() {

    var query: MutableLiveData<String> = MutableLiveData("")

    private val _characterList = MutableLiveData<Resource<List<Character?>>>()
    val characterList: LiveData<Resource<List<Character?>>>
        get() = _characterList

    private val _product = MutableLiveData<Resource<Character>>()
    val product: LiveData<Resource<Character>>
        get() = _product

    fun getCharacters() {
        _characterList.postValue(Resource.Loading())
        viewModelScope.launch {
            runCatching {
                _characterList.postValue(charactersRepository.getCharacters())
            }.onFailure { throwable ->
                _characterList.postValue(Resource.Error(throwable.message ?: "Error fetching result list from service"))
            }
        }
    }

    /*fun getCharacterDetails(id: String) {
        viewModelScope.launch {
            runCatching {
                _product.postValue(Resource.Loading())
                _product.postValue(charactersRepository.get(id))
            }.onFailure { throwable ->
                _productList.postValue(Resource.Error(throwable.message ?: "Error fetching result list from service"))
            }
        }
    }*/
}