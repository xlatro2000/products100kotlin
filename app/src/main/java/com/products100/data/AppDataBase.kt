package com.products100.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Provides

@Database(entities = [EntityBook::class], version = 1)
abstract class AppDataBase: RoomDatabase(){
    abstract fun booksDAO(): BooksDAO
}
