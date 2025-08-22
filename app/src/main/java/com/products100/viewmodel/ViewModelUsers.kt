package com.products100.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.products100.model.ResponseAPI
import com.products100.model.UserResult
import com.products100.repository.RepositoryAPIInterface
import com.products100.repository.RepositoryApiImpementation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import javax.inject.Inject


@HiltViewModel
class ViewModelUsers @Inject constructor(
    private val repository: RepositoryAPIInterface) : ViewModel()
{
    //private
    private val _listUsers = MutableStateFlow<List<UserResult>>(emptyList())
    //public
    val listUsers: StateFlow<List<UserResult>> get() = _listUsers


    init {
        viewModelScope.launch() {
            _listUsers.value = repository.GetUsers().results
    }

    }
}