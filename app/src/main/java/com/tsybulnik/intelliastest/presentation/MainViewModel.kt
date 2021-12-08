package com.tsybulnik.intelliastest.presentation


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tsybulnik.intelliastest.data.repository.WordRepositoryImpl
import com.tsybulnik.intelliastest.domain.GetWordItemUseCase
import com.tsybulnik.intelliastest.domain.WordItemDomain
import kotlinx.coroutines.launch

class MainViewModel(
) : ViewModel() {

    private var wordMutable = MutableLiveData<WordItemDomain>()
    val word:LiveData<WordItemDomain> = wordMutable

    private val repository:WordRepositoryImpl = WordRepositoryImpl()
    private val getWordItemUseCase = GetWordItemUseCase(repository)

    fun getData(word: String){
        viewModelScope.launch {
            wordMutable.value = getWordItemUseCase(word)
        }
    }
}