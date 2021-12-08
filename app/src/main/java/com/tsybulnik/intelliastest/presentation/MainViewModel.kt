package com.tsybulnik.intelliastest.presentation


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tsybulnik.intelliastest.data.repository.WordRepositoryImpl
import com.tsybulnik.intelliastest.domain.GetWordItemUseCase
import com.tsybulnik.intelliastest.domain.WordItemDomain
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private var wordMutable = MutableLiveData<WordItemDomain>()
    val word:LiveData<WordItemDomain> = wordMutable

    //    private val soundPhoneticMutable = MutableLiveData<String>()
//    val soundPhonetic: LiveData<String> = soundPhoneticMutable

    private val repository = WordRepositoryImpl()
    private val getWordItemUseCase = GetWordItemUseCase(repository)

    private val scope = CoroutineScope(Dispatchers.Main)

    fun getData(word: String){
        scope.launch {
            wordMutable.value = getWordItemUseCase(word)
        }
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }

//    fun soundPhonetic(sound:String){
//        val url:String = sound
//        val mediaPlayer = MediaPlayer().apply {
//            setAudioAttributes(
//                AudioAttributes.Builder()
//                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
//                    .setUsage(AudioAttributes.USAGE_MEDIA)
//                    .build()
//            )
//            setDataSource(url)
//            prepare() // might take long! (for buffering, etc)
//            start()
//        }
//    }
}