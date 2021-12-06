package com.tsybulnik.intelliastest.data.model

import com.tsybulnik.intelliastest.data.model.Definition

data class Meaning(
    val definitions: List<Definition>,
    val partOfSpeech: String
)