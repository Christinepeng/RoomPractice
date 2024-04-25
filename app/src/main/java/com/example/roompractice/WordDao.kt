package com.example.roompractice

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface WordDao {
    @Insert
    suspend fun insertWords(vararg words: Word)

    @Update
    suspend fun updateWords(vararg words: Word)

    @Delete
    suspend fun deleteWords(vararg words: Word)

    @Query("DELETE FROM WORD")
    suspend fun deleteAllWords()

    @Query("SELECT * FROM WORD ORDER BY ID DESC")
    suspend fun getAllWords(): List<Word>
}