package com.tsybulnik.intelliastest.domain



interface WordRepository {

   suspend fun getWord(word:String): WordItemDomain
}