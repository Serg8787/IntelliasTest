package com.tsybulnik.intelliastest.presentation

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.tsybulnik.intelliastest.R
import com.tsybulnik.intelliastest.data.model.WordItem
import com.tsybulnik.intelliastest.data.network.Api
import com.tsybulnik.intelliastest.data.network.RetrofitClient
import com.tsybulnik.intelliastest.presentation.adapters.WordAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    lateinit var tvMainWord: TextView
    lateinit var tvPhonetic: TextView
    lateinit var clPhonetic: ConstraintLayout
    lateinit var btSearch: Button
    lateinit var etWord: EditText
    lateinit var uri: String
    private lateinit var vm: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vm = ViewModelProvider(this).get(MainViewModel::class.java)



        tvMainWord = findViewById(R.id.tvMainWord)
        tvPhonetic = findViewById(R.id.tvPhonetic)
        etWord = findViewById(R.id.etEnterWord)
        clPhonetic = findViewById(R.id.clPhonetic)
        val recyclerView: RecyclerView = findViewById(R.id.recycler)

        btSearch = findViewById(R.id.btSearch)



        btSearch.setOnClickListener {
            hideKeybord()
            val word: String = etWord.text.toString()
            vm.loadData(word)
            vm.mainWord.observe(this, Observer {
                tvMainWord.text = it
            })
            vm.phonetic.observe(this, Observer {
                tvPhonetic.text = it
            })
            vm.mapPartOfSpeechDefinion.observe(this, Observer {
                recyclerView.adapter = WordAdapter(it)
            })
            vm.isPhonetic.observe(this, Observer {
                if(it){
                    clPhonetic.visibility = View.INVISIBLE
                } else {
                    clPhonetic.visibility = View.VISIBLE
                }
            })
            vm.errorRightWord.observe(this, Observer {
                Toast.makeText(this,it,Toast.LENGTH_LONG).show()
            })
            vm.errorRightResonse.observe(this, Observer {
                Toast.makeText(this,it,Toast.LENGTH_LONG).show()
            })
//            val retrofit =
//                RetrofitClient.getClient("https://api.dictionaryapi.dev/").create(Api::class.java)
//            retrofit.getDataFromWord(word).enqueue(object : Callback<List<WordItem>> {
//                override fun onResponse(
//                    call: Call<List<WordItem>>,
//                    response: Response<List<WordItem>>
//                ) {
//                    if (response.isSuccessful) {
////                        val size: Int = response.body()!!.last().meanings.size
////                        val arrayPartOfSpeech = arrayOfNulls<String?>(size)
////                        val arraydefinition = arrayOfNulls<String?>(size)
////                        for (i in 0 until size) {
////                            for (i in 0 until size) {
////                                arrayPartOfSpeech[i] =
////                                    response.body()!!.first().meanings[i].partOfSpeech
////                            }
////                        }
////                        for (i in 0 until size) {
////                            for (i in 0 until size) {
////                                arraydefinition[i] =
////                                    response.body()!!
////                                        .first().meanings[i].definitions.first().definition
////                            }
////                        }
////                        val map: Map<String?, String?> =
////                            arrayPartOfSpeech.zip(arraydefinition)
////                                .toMap()
////                        recyclerView.adapter = WordAdapter(map)
////                        tvMainWord.setText(response.body()!!.first().word)
////                        tvPhonetic.setText("[ ${response.body()!!.first().phonetic} ]")
//                        if (response.body()!!.first().phonetics.isNotEmpty()) {
//                            clPhonetic.visibility = View.VISIBLE
//                            uri = "https:" + response.body()!!.first().phonetics.first().audio
//                        } else {
//                            clPhonetic.visibility = View.INVISIBLE
//                        }
//                    } else {
//                        Toast.makeText(
//                            applicationContext,
//                            "Введите корректное слово",
//                            Toast.LENGTH_LONG
//                        )
//                            .show()
//                    }
//                }
//
//                override fun onFailure(call: Call<List<WordItem>>, t: Throwable) {
//                    Toast.makeText(
//                        applicationContext,
//                        "Ошибка запроса! Возможно пропал интернет",
//                        Toast.LENGTH_LONG
//                    ).show()
//                }
//            })
//            etWord.text.clear()
//        }
//        clPhonetic.setOnClickListener {
//            playSound()
//        }
//        }

//            fun playSound() {
//                val url = uri
//                val mediaPlayer = MediaPlayer().apply {
//                    setAudioAttributes(
//                        AudioAttributes.Builder()
//                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
//                            .setUsage(AudioAttributes.USAGE_MEDIA)
//                            .build()
//                    )
//                    setDataSource(url)
//                    prepare() // might take long! (for buffering, etc)
//                    start()
//                }
//
//            }
//

        }
    }
   private fun hideKeybord() {
        val view: View? = this.currentFocus
        if (view != null) {
            val imm: InputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0)
        }
    }
}

