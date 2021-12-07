package com.tsybulnik.intelliastest.presentation


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tsybulnik.intelliastest.data.repository.WordRepositoryImpl
import com.tsybulnik.intelliastest.domain.GetWordItemUseCase
import com.tsybulnik.intelliastest.domain.WordItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

        private val mainWordMutable = MutableLiveData<String>()
    val mainWord: LiveData<String> = mainWordMutable
//
//    private val phoneticWordMutable = MutableLiveData<String>()
//    val phonetic: LiveData<String> = phoneticWordMutable
//
//    private val mapPartOfSpeechDefinionMutable = MutableLiveData<Map<String?, String?>>()
//    val mapPartOfSpeechDefinion = mapPartOfSpeechDefinionMutable
//
//    private val isPhoneticMutable = MutableLiveData<Boolean>()
//    val isPhonetic = isPhoneticMutable
//
//    private val errorRightWordMutable = MutableLiveData<String>()
//    val errorRightWord: LiveData<String> = errorRightWordMutable
//
//    private val errorRightResponseMutable = MutableLiveData<String>()
//    val errorRightResponse: LiveData<String> = errorRightResponseMutable
//
//    private val soundPhoneticMutable = MutableLiveData<String>()
//    val soundPhonetic: LiveData<String> = soundPhoneticMutable
//
    private val repository = WordRepositoryImpl()
    private val getWordItemUseCase = GetWordItemUseCase(repository)

    private val scope =  CoroutineScope(Dispatchers.IO)

    val wordItem = getWordItemUseCase


    fun getWord(word:String){
        scope.launch {
          mainWordMutable.value = getWordItemUseCase(word).meanings.toString()
        }
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }











//    fun loadData(word: String) {
//        val retrofit = ApiFactory.getClient("https://api.dictionaryapi.dev/")
//            .create(Api::class.java)
//        retrofit.getDataFromWord(word).enqueue(object : Callback<List<WordItemDto>> {
//            override fun onResponse(
//                call: Call<List<WordItemDto>>,
//                response: Response<List<WordItemDto>>
//            ) {
//                if (response.isSuccessful) {
//                    mainWordMutable.value = response.body()!!.first().word
//                    phoneticWordMutable.value = "[ ${response.body()!!.first().phonetic} ]"
//                    val size: Int = response.body()!!.last().meanings.size
//                    val arrayPartOfSpeech = arrayOfNulls<String>(size)
//                    val arraydefinition = arrayOfNulls<String>(size)
//                    for (i in 0 until size) {
//                        for (i in 0 until size) {
//                            arrayPartOfSpeech[i] =
//                                response.body()!!.first().meanings[i].partOfSpeech
//                            arraydefinition[i] =
//                                response.body()!!
//                                    .first().meanings[i].definitions.first().definition
//                        }
//                    }
//                    mapPartOfSpeechDefinionMutable.value =
//                        arrayPartOfSpeech.zip(arraydefinition).toMap()
//                    if (response.body()!!.first().phonetics.isNotEmpty()) {
//                        isPhoneticMutable.value = false
////                        soundPhonetic("https:" + response.body()!!.first().phonetics.first().audio)
//                        } else {
//                         isPhoneticMutable.value= true
//                        }
//                } else {
//                  errorRightWordMutable.value = "Введите корректное слово на английском языке"
//                }
//            }
//
//            override fun onFailure(call: Call<List<WordItemDto>>, t: Throwable) {
//               errorRightResponseMutable.value = "Ошибка запроса! Возможно пропал интернет?"
//            }
//
//        })
//    }
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