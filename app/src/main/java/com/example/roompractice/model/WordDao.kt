package com.example.roompractice.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.roompractice.model.Word

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
    fun getAllWordsLive(): LiveData<List<Word>>
}