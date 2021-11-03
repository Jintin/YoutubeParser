package com.jintin.youtubeparser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val parser: YoutubeParser) : ViewModel() {

    private val _liveData = MutableLiveData<List<String>>()
    val liveData: LiveData<List<String>>
        get() = _liveData
    private val _stateLiveData = MutableLiveData<State>()
    val stateLiveData: LiveData<State>
        get() = _stateLiveData

    fun search(query: String) {
        viewModelScope.launch(Dispatchers.Main) {
            _stateLiveData.value = State.LOADING
            _liveData.value = parser.load(query)
            _stateLiveData.value = State.NORMAL
        }
    }
}