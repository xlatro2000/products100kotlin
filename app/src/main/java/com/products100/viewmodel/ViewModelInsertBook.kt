package com.products100.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.products100.model.BookModel
import com.products100.repository.RepositoryDAOImplementation

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelInsertBook @Inject constructor(
    private val repositoryDAO: RepositoryDAOImplementation
): ViewModel()
{
    private val _bookItem = MutableStateFlow(BookModel(bookname = "", bookauthor = "", bookimage = ""))
    val bookItem:StateFlow<BookModel> get() = _bookItem

    fun insertBook(book:BookModel){
        viewModelScope.launch {
            repositoryDAO.InsertBook(book)
        }
    }

}
