package com.tsybulnik.intelliastest.data.mapper

import com.tsybulnik.intelliastest.data.model.WordItemDto
import com.tsybulnik.intelliastest.domain.WordItem

class WordMapper {

    fun mapDtoModelToEntity(worfItemDto: WordItemDto) = WordItem(
        word = worfItemDto.word,
        meanings = worfItemDto.meanings,
        origin = worfItemDto.origin,
        phonetic = worfItemDto.phonetic,
        phonetics = worfItemDto.phonetics
    )
}


