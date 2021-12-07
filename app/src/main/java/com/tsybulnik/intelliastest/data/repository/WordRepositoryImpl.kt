package com.tsybulnik.intelliastest.data.repository

import com.tsybulnik.intelliastest.data.mapper.WordMapper
import com.tsybulnik.intelliastest.data.network.Api
import com.tsybulnik.intelliastest.data.network.ApiFactory
import com.tsybulnik.intelliastest.domain.WordItemDomain
import com.tsybulnik.intelliastest.domain.WordRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class WordRepositoryImpl(
    private val apiService: Api = ApiFactory.apiService


) : WordRepository {
    private val mapper = WordMapper()


    override suspend fun getWord(word: String): WordItemDomain {

        val wordItemDto= apiService.getDataFromWord(word)
         return mapper.mapDtoModelToEntityWordItem(wordItemDto)
    }
}