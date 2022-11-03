package com.example.memefon.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.memefon.data.model.Resource
import com.example.memefon.data.repository.MemefonRepository
import com.example.memefon.di.qualifiers.IoDispatcher
import com.example.memefon.domain.MemefonModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

@HiltViewModel
class MemefonViewModel @Inject constructor(
    private val memefonRepository: MemefonRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : ViewModel() {

    var query: MutableLiveData<String> = MutableLiveData("")

    private val _memefonList = MutableLiveData<Resource<List<MemefonModel>>>()
    val memefonList: LiveData<Resource<List<MemefonModel>>>
        get() = _memefonList

    fun getMemefons() {
        _memefonList.value = Resource.Loading()
        viewModelScope.launch(dispatcher) {
            runCatching { memefonRepository.getMemefonData() }
                .fold({
                    withContext(viewModelScope.coroutineContext) {
                        _memefonList.value = if (it is Resource.Error) {
                            Resource.Error(it.message.orEmpty())
                        } else {
                            Resource.Success(it.data.orEmpty())
                        }
                    }
                }, { throwable ->
                    withContext(viewModelScope.coroutineContext) {
                        _memefonList.value = Resource.Error(throwable.message ?: "Error fetching result list from mock service")
                    }
                })
        }
    }
}
