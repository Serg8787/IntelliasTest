package com.tsybulnik.intelliastest.presentation

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
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
    lateinit var clPhonetic:ConstraintLayout
    lateinit var btSearch: Button
    lateinit var etWord: EditText
    lateinit var uri: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvMainWord = findViewById(R.id.tvMainWord)
        tvPhonetic = findViewById(R.id.tvPhonetic)
        etWord = findViewById(R.id.etEnterWord)
        clPhonetic = findViewById(R.id.clPhonetic)
        val recyclerView: RecyclerView = findViewById(R.id.recycler)

        btSearch = findViewById(R.id.btSearch)
        btSearch.setOnClickListener {
            hideKeybord()
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
                                    response.body()!!.first().meanings[i].partOfSpeech
                            }
                            Log.d("MyLog", "arrayPartOfSpeech " + arrayPartOfSpeech[i].toString())
                        }
                        for (i in 0 until size) {
                            for (i in 0 until size) {
                                arraydefinition[i] =
                                    response.body()!!.first().meanings[i].definitions.first().definition
                            }
                            Log.d("MyLog", "arraydefinition " + arraydefinition[i].toString())
                        }
                        val map: Map<String?, String?> =
                            arrayPartOfSpeech.zip(arraydefinition)
                                .toMap()
                        Log.d("MyLog", "map " + map)
                        recyclerView.adapter = WordAdapter(map)
                        tvMainWord.setText(response.body()!!.first().word)
                        tvPhonetic.setText("[ ${response.body()!!.first().phonetic} ]")
                        if (response.body()!!.first().phonetics.isNotEmpty()){
                            clPhonetic.visibility = View.VISIBLE
                            uri = "https:" + response.body()!!.first().phonetics.first().audio
                            Log.d("MyLog", "audio " + response.body()!!.last().phonetics.first().audio)
                        } else {
                            Log.d("MyLog", " not audio")
                            clPhonetic.visibility = View.INVISIBLE
                        }
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
                    Toast.makeText(applicationContext, "Ошибка запроса! Возможно пропал интернет", Toast.LENGTH_LONG).show()
                }
            })
            etWord.text.clear()
        }
        clPhonetic.setOnClickListener {
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
    fun hideKeybord(){
        val view: View? = this.currentFocus
            if (view != null) {
                val imm: InputMethodManager =
                    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0)
            }
    }
}