package com.tsybulnik.intelliastest.presentation


import android.text.BoringLayout
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tsybulnik.intelliastest.data.model.WordItem
import com.tsybulnik.intelliastest.data.network.Api
import com.tsybulnik.intelliastest.data.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val mainWordMutable = MutableLiveData<String>()
    val mainWord: LiveData<String> = mainWordMutable
    private val phoneticWordMutable = MutableLiveData<String>()
    val phonetic: LiveData<String> = phoneticWordMutable
    private val mapPartOfSpeechDefinionMutable = MutableLiveData<Map<String?, String?>>()
    val mapPartOfSpeechDefinion = mapPartOfSpeechDefinionMutable

    private val isPhoneticMutable = MutableLiveData<Boolean>()
    val isPhonetic = isPhoneticMutable

    private val errorRightWordMutable = MutableLiveData<String>()
    val errorRightWord: LiveData<String> = errorRightWordMutable

    private val errorRightResponseMutable = MutableLiveData<String>()
    val errorRightResonse: LiveData<String> = errorRightResponseMutable


    fun loadData(word: String) {
        val retrofit = RetrofitClient.getClient("https://api.dictionaryapi.dev/")
            .create(Api::class.java)
        retrofit.getDataFromWord(word).enqueue(object : Callback<List<WordItem>> {
            override fun onResponse(
                call: Call<List<WordItem>>,
                response: Response<List<WordItem>>
            ) {
                if (response.isSuccessful) {
                    mainWordMutable.value = response.body()!!.first().word
                    phoneticWordMutable.value = "[ ${response.body()!!.first().phonetic} ]"
                    val size: Int = response.body()!!.last().meanings.size
                    val arrayPartOfSpeech = arrayOfNulls<String>(size)
                    val arraydefinition = arrayOfNulls<String>(size)
                    for (i in 0 until size) {
                        for (i in 0 until size) {
                            arrayPartOfSpeech[i] =
                                response.body()!!.first().meanings[i].partOfSpeech
                            arraydefinition[i] =
                                response.body()!!
                                    .first().meanings[i].definitions.first().definition
                        }
                    }
                    mapPartOfSpeechDefinionMutable.value =
                        arrayPartOfSpeech.zip(arraydefinition).toMap()
                    if (response.body()!!.first().phonetics.isNotEmpty()) {
                        isPhoneticMutable.value = false
//                            uri = "https:" + response.body()!!.first().phonetics.first().audio
                        } else {
                         isPhoneticMutable.value= true
                        }
                } else {
                  errorRightWordMutable.value = "Введите корректное слово на английском языке"
                }
            }

            override fun onFailure(call: Call<List<WordItem>>, t: Throwable) {
               errorRightResponseMutable.value = "Ошибка запроса! Возможно пропал интернет?"
            }

        })
    }
}