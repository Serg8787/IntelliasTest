package com.tsybulnik.intelliastest.presentation

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.tsybulnik.intelliastest.Api
import com.tsybulnik.intelliastest.R
import com.tsybulnik.intelliastest.RetrofitClient
import com.tsybulnik.intelliastest.WordAdapter
import com.tsybulnik.intelliastest.domain.entities.WordItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    lateinit var tvMainWord: TextView
    lateinit var tvPhonetic: TextView
    lateinit var btSound: ImageButton
    lateinit var btSearch: Button
    lateinit var etWord: EditText
    lateinit var uri: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvMainWord = findViewById(R.id.tvMainWord)
        tvPhonetic = findViewById(R.id.tvPhonetic)
        btSound = findViewById(R.id.btSound)
        etWord = findViewById(R.id.etEnterWord)
        val recyclerView: RecyclerView = findViewById(R.id.recycler)

        btSearch = findViewById(R.id.btSearch)
        btSearch.setOnClickListener {
            val word: String = etWord.text.toString()
            val retrofit =
                RetrofitClient.getClient("https://api.dictionaryapi.dev/").create(Api::class.java)
            retrofit.getDataFromWord(word).enqueue(object : Callback<List<WordItem>> {
                override fun onResponse(
                    call: Call<List<WordItem>>,
                    response: Response<List<WordItem>>
                ) {
                    if (response.isSuccessful) {
                        val size: Int = response.body()!!.last().meanings.size
                        Log.d("MyLog", response.isSuccessful.toString())
                        val arrayPartOfSpeech = arrayOfNulls<String?>(size)
                        val arraydefinition = arrayOfNulls<String?>(size)
                        for (i in 0 until size) {
                            for (i in 0 until size) {
                                arrayPartOfSpeech[i] =
                                    response.body()!!.last().meanings[i].partOfSpeech
                            }
                            Log.d("MyLog", "arrayPartOfSpeech " + arrayPartOfSpeech[i].toString())
                        }
                        for (i in 0 until size) {
                            for (i in 0 until size) {
                                arraydefinition[i] =
                                    response.body()!!.last().meanings[i].definitions[0].definition
                            }
                            Log.d("MyLog", "arraydefinition " + arraydefinition[i].toString())
                        }
                        val map: Map<String?, String?> =
                            arrayPartOfSpeech.zip(arraydefinition)
                                .toMap()
                        Log.d("MyLog", "map " + map)
                        recyclerView.adapter = WordAdapter(map)
                        tvMainWord.setText(response.body()!!.last().word)
                        tvPhonetic.setText("[ ${response.body()!!.last().phonetic} ]")
                        uri = "https:" + response.body()!!.last().phonetics[0].audio

                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Введите корректное слово",
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }
                }
                override fun onFailure(call: Call<List<WordItem>>, t: Throwable) {
                    Toast.makeText(applicationContext, "Ошибка", Toast.LENGTH_LONG).show()
                }
            })
            etWord.text.clear()
        }

        btSound.setOnClickListener {
            playSound()
        }
    }

    fun playSound() {
        val url = uri
        val mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(url)
            prepare() // might take long! (for buffering, etc)
            start()
        }

    }
}