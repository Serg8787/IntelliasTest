package com.tsybulnik.intelliastest.domain

class GetWordItemUseCase(private val repository: WordRepository) {
    suspend operator fun invoke(word: String) = repository.getWord(word = word)
}