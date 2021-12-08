package com.tsybulnik.intelliastest.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_data_table")
data class WordItemDto(
    @PrimaryKey
    val meanings: List<MeaningDto>,
    val origin: String?,
    val phonetic: String,
    val phonetics: List<PhoneticDto>,
    val word: String
)