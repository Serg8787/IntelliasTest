package com.tsybulnik.intelliastest.data.repository

import androidx.lifecycle.LiveData
import com.tsybulnik.intelliastest.data.mapper.WordMapper
import com.tsybulnik.intelliastest.data.network.Api
import com.tsybulnik.intelliastest.data.network.ApiFactory
import com.tsybulnik.intelliastest.domain.WordItem
import com.tsybulnik.intelliastest.domain.WordRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class WordRepositoryImpl(
    private val apiService: Api = ApiFactory.apiService


) : WordRepository {
    private val mapper = WordMapper()
    val customScope = CoroutineScope(Dispatchers.Main)

    override suspend fun getWord(word: String): WordItem{

        val wordItemDto = apiService.getDataFromWord(word)
        return mapper.mapDtoModelToEntity(wordItemDto)

    }
}