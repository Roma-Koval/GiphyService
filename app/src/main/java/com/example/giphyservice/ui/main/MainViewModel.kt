package com.example.giphyservice.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.giphyservice.data.repository.GifsRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// The ViewModel retrieves data from the Model and exposes it to the View through observable properties or LiveData objects.
// It also communicates with the Model to update data based on user actions
//
// The ViewModel exists from when you first request a ViewModel until the activity is finished and destroyed.
class MainViewModel(private val gifsRepository: GifsRepository) : ViewModel() {

    private val state = MutableLiveData<UIState>()
    fun getObjectData(): LiveData<UIState> = state

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError(throwable)
    }

    init {
        loadData()
    }

    fun loadData() {
        state.value = UIState.Loading

        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = gifsRepository.getGifsData()
            withContext(Dispatchers.Main) {
                val body = response.body()
                if (response.isSuccessful) {
                    if (body == null) {
                        state.value = UIState.Success(listOf())
                    } else {
                        state.value = UIState.Success(body.res)
                    }
                } else {
                    onError(null)
                }
            }
        }
    }

    private fun onError(message: Throwable?) {
        state.postValue(UIState.Error(message))
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}