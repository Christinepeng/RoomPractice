package com.example.roompractice

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// singleton
@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class WordDatabase: RoomDatabase() {
    private var INSTANCE: WordDatabase? = null
    @Synchronized
    fun getDatabase(context: Context): WordDatabase {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.applicationContext, WordDatabase::class.java, "word_database")
                .allowMainThreadQueries()
                .build()
        }
        return INSTANCE as WordDatabase
    }

    abstract fun wordDao(): WordDao
}