package com.products100.data


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books_table")
data class EntityBook(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "bookid") val bookid:Int = 0,
    @ColumnInfo(name = "bookname") val bookname: String,
    @ColumnInfo(name = "bookauthor") val bookauthor: String,
    @ColumnInfo(name = "bookimage") val bookimage: String
)

