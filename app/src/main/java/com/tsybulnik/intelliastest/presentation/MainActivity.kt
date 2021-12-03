package com.tsybulnik.intelliastest.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.tsybulnik.intelliastest.Api
import com.tsybulnik.intelliastest.R
import com.tsybulnik.intelliastest.RetrofitClient
import com.tsybulnik.intelliastest.domain.entities.Meaning
import com.tsybulnik.intelliastest.domain.entities.Word
import com.tsybulnik.intelliastest.domain.entities.WordItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var tvMainWord:TextView
    lateinit var tvPhonetic:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        Log.d("MyLog","Тест")

        tvMainWord = findViewById(R.id.tvMainWord)
        tvPhonetic = findViewById(R.id.tvPhonetic)

        val retrofit =
            RetrofitClient.getClient("https://api.dictionaryapi.dev/").create(Api::class.java)
        retrofit.getDataFromWord("hello").enqueue(object : Callback<List<WordItem>> {
            override fun onResponse(
                call: Call<List<WordItem>>,
                response: Response<List<WordItem>>
            ) {
                Log.d("MyLog", "Тест " + response.body()!!.last().meanings[1].definitions)
                Log.d("MyLog", "meanings.size " + response.body()!!.last().meanings.size)
                Log.d("MyLog", "meanings.toHashSet() " + response.body()!!.last().meanings[0].partOfSpeech)
                Log.d("MyLog", "meanings.toHashSet() " + response.body()!!.last().meanings[0].definitions[0].definition)
                val size:Int = response.body()!!.last().meanings.size
                for(i in 0..size){
                    val map = mutableMapOf<String,String>()

                    map.put(response.body()!!.last().meanings[i].partOfSpeech,response.body()!!.last().meanings[i].definitions[i].definition)
                    Log.d("MyLog", "map " + map.toString() )
                }
//

                tvMainWord.setText(response.body()!!.last().word)
                tvPhonetic.setText(response.body()!!.last().phonetic)

            }

            override fun onFailure(call: Call<List<WordItem>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}