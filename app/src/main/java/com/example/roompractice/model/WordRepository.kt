package com.example.roompractice.model

import androidx.lifecycle.LiveData

class WordRepository(private val wordDao: WordDao) {
    fun getAllWords(): LiveData<List<Word>> {
        return wordDao.getAllWordsLive()
    }

    suspend fun insertWords(vararg words: Word) {
        wordDao.insertWords(*words)
    }

    suspend fun updateWord(word: Word) {
        wordDao.updateWords(word)
    }

    suspend fun deleteAllWords() {
        wordDao.deleteAllWords()
    }

    suspend fun deleteWord(word: Word) {
        wordDao.deleteWords(word)
    }
}