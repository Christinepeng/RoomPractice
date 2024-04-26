package com.example.roompractice.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roompractice.R
import com.example.roompractice.model.Word
import com.example.roompractice.databinding.ActivityMainBinding
import com.example.roompractice.viewModel.WordViewModel

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var viewModel: WordViewModel
    private lateinit var myAdapter: MyAdapter

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
        myAdapter = MyAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = myAdapter

        viewModel.allWordsLive.observe(this) {words ->
            myAdapter.setAllWords(words)
            myAdapter.notifyDataSetChanged()
        }

        binding.buttonInsert.setOnClickListener {
            val englishNames = listOf(
                "Snoopy",
                "Charlie Brown",
                "Lucy van Pelt",
                "Linus van Pelt",
                "Sally Brown",
                "Schroeder",
                "Woodstock",
                "Peppermint Patty",
                "Marcie",
                "Franklin",
                "Rerun van Pelt",
                "Spike",
                "Belle",
                "Marbles",
                "Molly Volley",
                "Andy",
                "Olaf",
                "Frieda",
                "Roy",
                "Eudora"
            )

            val chineseTranslations = listOf(
                "史努比",
                "查理·布朗",
                "露西·范佩林",
                "林納斯·范佩林",
                "莎莉·布朗",
                "施羅德",
                "伍德斯托克",
                "薄荷派蒂",
                "瑪西",
                "富蘭克林",
                "雷朗·范佩林",
                "斯派克",
                "貝爾",
                "馬爾布",
                "莫莉·沃利",
                "安迪",
                "奧拉夫",
                "弗莉達",
                "羅伊",
                "尤多拉"
            )
            for (i in 0..englishNames.size - 1) {
                viewModel.insertWords(Word(word = englishNames[i], chineseMeaning = chineseTranslations[i]))
            }
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