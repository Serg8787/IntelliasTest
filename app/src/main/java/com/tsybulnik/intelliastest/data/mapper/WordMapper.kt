package com.tsybulnik.intelliastest.data.mapper

import com.tsybulnik.intelliastest.data.model.WordItemDto
import com.tsybulnik.intelliastest.domain.WordItemDomain

class WordMapper {

    fun mapDtoModelToEntityWordItem(wordItemDtoList: List<WordItemDto>) = WordItemDomain(
        word = wordItemDtoList[0].word,
        phonetics = wordItemDtoList[0].phonetics,
        phonetic = wordItemDtoList[0].phonetic,
        origin = wordItemDtoList[0].origin,
        meanings = wordItemDtoList[0].meanings,

        )


}


