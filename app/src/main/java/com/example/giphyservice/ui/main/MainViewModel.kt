package com.example.giphyservice.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.giphyservice.data.repository.GifsRepository
import com.example.giphyservice.data.repository.GifsRepositoryResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// The ViewModel retrieves data from the Model and exposes it to the View through observable properties or LiveData objects.
// It also communicates with the Model to update data based on user actions
//
// The ViewModel exists from when you first request a ViewModel until the activity is finished and destroyed.
class MainViewModel(private val gifsRepository: GifsRepository) : ViewModel() {

    private val state = MutableLiveData<UIState>()
    fun getObjectData(): LiveData<UIState> = state

    init {
        loadData()
    }

    fun loadData() {
        state.value = UIState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = gifsRepository.getGifsData()
                withContext(Dispatchers.Main) {
                    state.value = mapRepositoryToUIState(result)
                }
            } catch (ex: Exception) {
                state.postValue(UIState.Error(ex))
            }
        }
    }

    private fun mapRepositoryToUIState(repositoryResult: GifsRepositoryResult) : UIState {
       return when (repositoryResult) {
            is GifsRepositoryResult.Success -> UIState.Success(repositoryResult.dataResult.res)
            is GifsRepositoryResult.Error -> UIState.Error(repositoryResult.error)
        }
    }
}
