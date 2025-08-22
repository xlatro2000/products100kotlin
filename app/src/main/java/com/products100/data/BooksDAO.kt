package com.products100.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface BooksDAO{

    @Query("SELECT * FROM books_table")
    suspend fun getAllBooks(): List<EntityBook>

    @Insert
    suspend fun insertBook(entity: EntityBook)

    @Query("DELETE FROM books_table WHERE bookid = :idBook")
    suspend fun deleteBook(idBook: Int?): Int

    @Update
    suspend fun updateBook(entity: EntityBook): Int
}