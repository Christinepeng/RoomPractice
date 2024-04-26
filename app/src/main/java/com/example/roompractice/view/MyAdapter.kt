package com.example.roompractice.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roompractice.R
import com.example.roompractice.model.Word

class MyAdapter() : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private var allWords: List<Word> = emptyList()

    fun setAllWords(allWords: List<Word>) {
        this.allWords = allWords
    }
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewNumber: TextView = itemView.findViewById(R.id.textViewNumber)
        val textViewEnglish: TextView = itemView.findViewById(R.id.textViewEnglish)
        val textViewChinese: TextView = itemView.findViewById(R.id.textViewChinese)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_normal, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val word = allWords[position]
        holder.textViewNumber.text = (position + 1).toString()
        holder.textViewEnglish.text = word.word
        holder.textViewChinese.text = word.chineseMeaning
    }

    override fun getItemCount(): Int {
//        return allWords.size
        return if (allWords.isEmpty()) 0 else allWords.size
    }
}