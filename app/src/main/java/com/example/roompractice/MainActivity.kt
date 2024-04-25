package com.example.roompractice

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.roompractice.databinding.ActivityMainBinding
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    lateinit var wordDao: WordDao
    lateinit var wordDatabase: WordDatabase
    lateinit var allWordsLive: LiveData<List<Word>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        wordDatabase = Room.databaseBuilder(this, WordDatabase::class.java, "word_database")
            .allowMainThreadQueries()
            .build()

        wordDao = wordDatabase.wordDao()
        allWordsLive = wordDao.getAllWordsLive()
        allWordsLive.observe(this) {words ->
            binding.textView.text = words.joinToString(separator = "") { "${it.id} : ${it.word} = ${it.chineseMeaning}\n" }
        }

        binding.buttonInsert.setOnClickListener {
            lifecycleScope.launch {
                val word1 = Word(word = "Hello", chineseMeaning = "你好")
                val word2 = Word(word = "World", chineseMeaning = "世界")
                wordDao.insertWords(word1, word2)
            }
        }
        binding.buttonUpdate.setOnClickListener {
            lifecycleScope.launch {
                val word1 = Word(word = "Snoopy", chineseMeaning = "史努比")
                word1.id = 10
                wordDao.updateWords(word1)
            }
        }
        binding.buttonClear.setOnClickListener {
            lifecycleScope.launch {
                wordDao.deleteAllWords()
            }
        }
        binding.buttonDelete.setOnClickListener {
            lifecycleScope.launch {
                val word1 = Word(word = "Snoopy", chineseMeaning = "史努比")
                word1.id = 10
                wordDao.deleteWords(word1)
            }
        }
    }
}