package com.tsybulnik.intelliastest.domain.entities

data class Meaning(
    val definitions: List<Definition>,
    val partOfSpeech: String
)