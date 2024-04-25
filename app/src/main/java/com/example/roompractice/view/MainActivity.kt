package com.example.roompractice.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.roompractice.R
import com.example.roompractice.model.Word
import com.example.roompractice.databinding.ActivityMainBinding
import com.example.roompractice.viewModel.WordViewModel


class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var viewModel: WordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viewModel = ViewModelProvider(this).get(WordViewModel::class.java)

        viewModel.allWordsLive.observe(this) {words ->
            binding.textView.text = words.joinToString(separator = "") { "${it.id} : ${it.word} = ${it.chineseMeaning}\n" }
        }

        binding.buttonInsert.setOnClickListener {
            viewModel.insertWords(
                Word(word = "Hello", chineseMeaning = "你好"),
                Word(word = "World", chineseMeaning = "世界")
            )
        }
        binding.buttonUpdate.setOnClickListener {
            val word = Word(word = "Snoopy", chineseMeaning = "史努比").apply { id = 270 }
            viewModel.updateWord(word)
        }
        binding.buttonClear.setOnClickListener {
            viewModel.deleteAllWords()
        }
        binding.buttonDelete.setOnClickListener {
            val word = Word(word = "Snoopy", chineseMeaning = "史努比").apply { id = 270 }
            viewModel.deleteWord(word)
        }
    }
}