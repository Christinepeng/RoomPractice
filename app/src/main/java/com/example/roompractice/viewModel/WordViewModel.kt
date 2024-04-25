package com.example.roompractice.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.roompractice.model.Word
import com.example.roompractice.model.WordDao
import com.example.roompractice.model.WordDatabase
import kotlinx.coroutines.launch

class WordViewModel(application: Application) : AndroidViewModel(application) {
    private val wordDao: WordDao = WordDatabase.getDatabase(application).wordDao()
    val allWordsLive: LiveData<List<Word>> = wordDao.getAllWordsLive()

    fun insertWords(word1: Word, word2: Word) {
        viewModelScope.launch {
            wordDao.insertWords(word1, word2)
        }
    }

    fun updateWord(word: Word) {
        viewModelScope.launch {
            wordDao.updateWords(word)
        }
    }

    fun deleteAllWords() {
        viewModelScope.launch {
            wordDao.deleteAllWords()
        }
    }

    fun deleteWord(word: Word) {
        viewModelScope.launch {
            wordDao.deleteWords(word)
        }
    }
}