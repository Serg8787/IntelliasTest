package com.tsybulnik.intelliastest.data.mapper

import com.tsybulnik.intelliastest.data.model.WordItemDto
import com.tsybulnik.intelliastest.domain.WordItemDomain

class WordMapper {

    fun mapDtoModelToEntityWordItem(wordItemDtoList: ArrayList<WordItemDto>): WordItemDomain {

        return WordItemDomain(
            word = wordItemDtoList.first().word,
            phonetics = wordItemDtoList.first().phonetics,
            phonetic = wordItemDtoList.first().phonetic,
            origin = wordItemDtoList.first().origin,
            meanings = wordItemDtoList.first().meanings
        )


    }

}


