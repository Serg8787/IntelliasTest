package com.tsybulnik.intelliastest.data.repository

import android.util.Log
import com.tsybulnik.intelliastest.data.mapper.WordMapper
import com.tsybulnik.intelliastest.data.network.Api
import com.tsybulnik.intelliastest.data.network.ApiFactory
import com.tsybulnik.intelliastest.domain.WordItemDomain
import com.tsybulnik.intelliastest.domain.WordRepository

class WordRepositoryImpl(
    private val apiService: Api = ApiFactory.apiService
) : WordRepository {
    private val mapper = WordMapper()


    override suspend fun getWord(word: String): WordItemDomain {
        return try {
            val wordItemDto= apiService.getDataFromWord(word)
            mapper.mapDtoModelToEntityWordItem(wordItemDto)
        } catch (e:Exception){
            Log.d("MyLog",e.toString())
            WordItemDomain(emptyList(), "", "", emptyList(),
                "No Definitions Found")
        }
    }
}