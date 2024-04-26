package com.example.roompractice.view

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roompractice.R
import com.example.roompractice.model.Word
import com.example.roompractice.viewModel.WordViewModel

class MyAdapter(val useCardView: Boolean, val wordViewModel: WordViewModel) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private var allWords: List<Word> = emptyList()

    fun setAllWords(allWords: List<Word>) {
        this.allWords = allWords
        notifyDataSetChanged()
    }
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewNumber: TextView = itemView.findViewById(R.id.textViewNumber)
        val textViewEnglish: TextView = itemView.findViewById(R.id.textViewEnglish)
        val textViewChinese: TextView = itemView.findViewById(R.id.textViewChinese)
        @SuppressLint("UseSwitchCompatOrMaterialCode")
        val aSwitchChineseVisible: Switch = itemView.findViewById(R.id.switchChineseInvisible)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        val layout = if (useCardView) R.layout.cell_card else R.layout.cell_normal
        val layout = if (useCardView) R.layout.cell_card_2 else R.layout.cell_normal_2
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val word = allWords[position]
        holder.textViewNumber.text = (position + 1).toString()
        holder.textViewEnglish.text = word.word
        holder.textViewChinese.text = word.chineseMeaning

        holder.aSwitchChineseVisible.setOnCheckedChangeListener(null)

        if (word.chineseInvisible) {
            holder.textViewChinese.visibility = View.GONE
            holder.aSwitchChineseVisible.isChecked = true
        } else {
            holder.textViewChinese.visibility = View.VISIBLE
            holder.aSwitchChineseVisible.isChecked = false
        }
        holder.itemView.setOnClickListener {
            val uri = Uri.parse("https://translate.google.com/?sl=en&tl=zh-TW&text=" + holder.textViewEnglish.text)
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(uri)
            holder.itemView.context.startActivity(intent)
        }

        holder.aSwitchChineseVisible.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                holder.textViewChinese.visibility = View.GONE
                word.chineseInvisible = true
                wordViewModel.updateWord(word)
            } else {
                holder.textViewChinese.visibility = View.VISIBLE
                word.chineseInvisible = false
                wordViewModel.updateWord(word)
            }
        }
    }

    override fun getItemCount(): Int {
//        return allWords.size
        return if (allWords.isEmpty()) 0 else allWords.size
    }
}