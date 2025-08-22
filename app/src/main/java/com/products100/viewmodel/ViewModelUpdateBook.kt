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
class ViewModelUpdateBook @Inject constructor(
    private val repositoryDAO: RepositoryDAOImplementation
): ViewModel()
{
    private val _bookUpdated = MutableStateFlow(0)
    val bookUpdated:StateFlow<Int> get() = _bookUpdated

    fun updateBook(book:BookModel){
        viewModelScope.launch {
            repositoryDAO.UpdateBook(book)
        }
    }

}
