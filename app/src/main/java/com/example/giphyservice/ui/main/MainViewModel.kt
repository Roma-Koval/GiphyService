package com.example.giphyservice.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.giphyservice.data.repository.GifsRepository
import com.example.giphyservice.ui.Gif
import kotlinx.coroutines.launch

// The ViewModel retrieves data from the Model and exposes it to the View through observable properties or LiveData objects.
// It also communicates with the Model to update data based on user actions
//
// The ViewModel exists from when you first request a ViewModel until the activity is finished and destroyed.
class MainViewModel(private val gifsRepository: GifsRepository) : ViewModel() {

    private val state = MutableLiveData<UIState<List<Gif>>>()
    fun getObjectData(): LiveData<UIState<List<Gif>>> = state

    init {
        loadData()
    }

    fun loadData() {
        state.value = UIState.Loading

        viewModelScope.launch {
            state.value = gifsRepository.getGifsData().mapRepositoryToUIState()
        }
    }
}
