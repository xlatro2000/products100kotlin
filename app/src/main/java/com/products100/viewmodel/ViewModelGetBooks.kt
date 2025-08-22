package com.products100.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.products100.model.BookModel
import com.products100.model.ResponseAPI
import com.products100.model.UserResult
import com.products100.repository.RepositoryAPIInterface
import com.products100.repository.RepositoryApiImpementation
import com.products100.repository.RepositoryDAOImplementation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import javax.inject.Inject


@HiltViewModel
class ViewModelGetBooks @Inject constructor(
    private val repository: RepositoryDAOImplementation) : ViewModel()
{
    //private
    private val _listBooks = MutableStateFlow<List<BookModel>>(emptyList())
    private val _idBook = MutableStateFlow<Int>(0)
    private val _updated = MutableStateFlow<Int>(0)

    //public
    val listBooks: StateFlow<List<BookModel>> get() = _listBooks
    val bookIdView : StateFlow<Int> get() = _idBook
    val updated : StateFlow<Int> get() = _updated




    init {
        viewModelScope.launch() {
            _listBooks.value = repository.getAllBooks()
        }
    }


    fun deleteBook(idBook: Int?)
    {
        viewModelScope.launch {
            _idBook.value = repository.DeleteBook(idBook)
        }
        //refresh the list
        _listBooks.value = _listBooks.value.filter { it.bookid != idBook }
    }
}