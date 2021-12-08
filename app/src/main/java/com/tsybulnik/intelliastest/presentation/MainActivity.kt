package com.tsybulnik.intelliastest.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.tsybulnik.intelliastest.R
import com.tsybulnik.intelliastest.presentation.adapters.WordAdapter


class MainActivity : AppCompatActivity() {
    lateinit var tvMainWord: TextView
    lateinit var tvPhonetic: TextView
    lateinit var clPhonetic: ConstraintLayout
    lateinit var btSearch: Button
    lateinit var etWord: EditText
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
            vm.getData(word)
            vm.word.observe(this, Observer {
                tvMainWord.text = it.word
                tvPhonetic.text = it.phonetic
                val size: Int = it.meanings.size
                val arraydefinition = arrayOfNulls<String>(size)
                val arrayPartOfSpeech = arrayOfNulls<String>(size)
                for (i in 0 until size) {
                    arrayPartOfSpeech[i] = it.meanings[i].partOfSpeech
                    arraydefinition[i] = it.meanings[i].definitions.first().definition
                }
                val map = arrayPartOfSpeech.zip(arraydefinition).toMap()
                recyclerView.adapter = WordAdapter(map)

            })
            etWord.text.clear()

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


