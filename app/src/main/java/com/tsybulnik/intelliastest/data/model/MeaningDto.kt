package com.tsybulnik.intelliastest.data.model

data class MeaningDto(
    val definitions: List<DefinitionDto>,
    val partOfSpeech: String
)