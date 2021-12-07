package com.tsybulnik.intelliastest.domain

import com.tsybulnik.intelliastest.data.model.MeaningDto
import com.tsybulnik.intelliastest.data.model.PhoneticDto

data class WordItemDomain(
    val meanings: List<MeaningDto>,
    val origin: String,
    val phonetic: String,
    val phonetics: List<PhoneticDto>,
    val word: String
)