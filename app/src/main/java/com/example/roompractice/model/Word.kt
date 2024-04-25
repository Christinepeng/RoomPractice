package com.example.roompractice.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.net.IDN

@Entity
data class Word(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    @ColumnInfo(name = "english_word")
    val word: String,

    @ColumnInfo(name = "chinese_meaning")
    val chineseMeaning: String,
)
