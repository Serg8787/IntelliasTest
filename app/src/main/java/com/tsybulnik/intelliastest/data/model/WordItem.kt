package com.tsybulnik.intelliastest.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_data_table")
data class WordItem(
    @PrimaryKey
    val meanings: List<Meaning>,
    val origin: String,
    val phonetic: String,
    val phonetics: List<Phonetic>,
    val word: String
)