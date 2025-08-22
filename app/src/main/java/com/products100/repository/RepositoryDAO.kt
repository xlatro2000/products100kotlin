package com.products100.repository

import com.products100.data.BooksDAO
import com.products100.data.EntityBook
import com.products100.model.BookModel

import javax.inject.Inject

//interface RepositoryDAOInterface {
//
//    suspend fun getAllBooks(): List<BookModel>
//
//    suspend fun InsertBook(book: BookModel)
//
//    suspend fun UpdateBook(book: BookModel)
//
//    suspend fun DeleteBook(book: BookModel)
//}



class RepositoryDAOImplementation @Inject constructor (
    private val datasource: BooksDAO
)
{

    suspend fun getAllBooks(): List<BookModel> {
        val listDAO = datasource.getAllBooks()
        val listBooks = listDAO.map{BookModel(
                                it.bookid,
                                it.bookname,
                                it.bookauthor,
                                it.bookimage
        )}
        return listBooks
    }

    suspend fun InsertBook(book: BookModel) {
        return datasource.insertBook(
            EntityBook(
                bookname = book.bookname,
                bookauthor = book.bookauthor,
                bookimage = book.bookimage)
        )
    }

    suspend fun UpdateBook(book: BookModel): Int {
        return datasource.updateBook(
            EntityBook(
                bookname = book.bookname,
                bookauthor = book.bookauthor,
                bookimage = book.bookimage)
        )
    }

    suspend fun DeleteBook(idBook: Int?): Int {
        return datasource.deleteBook(idBook)
    }

}