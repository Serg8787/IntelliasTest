package com.tsybulnik.intelliastest.domain.entities

data class WordItem(
    val meanings: List<Meaning>,
    val origin: String,
    val phonetic: String,
    val phonetics: List<Phonetic>,
    val word: String
)