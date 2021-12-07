package com.tsybulnik.intelliastest.presentation

import android.content.Context
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
            vm.getWord(word)
            vm.mainWord.observe(this, Observer {
                tvMainWord.text = it
            })
            vm.phonetic.observe(this, Observer {
                tvPhonetic.text = it
            })
            vm.mapPartOfSpeechDefinion.observe(this, Observer {
                recyclerView.adapter = WordAdapter(it)
            })


            Log.d("MyLog",vm.wordItem.toString())


//            vm.loadData(word)
//            vm.mainWord.observe(this, Observer {
//                tvMainWord.text = it
//            })
//            vm.phonetic.observe(this, Observer {
//                tvPhonetic.text = it
//            })
//            vm.mapPartOfSpeechDefinion.observe(this, Observer {
//                recyclerView.adapter = WordAdapter(it)
//            })
//            vm.isPhonetic.observe(this, Observer {
//                if (it) {
//                    clPhonetic.visibility = View.GONE
//                } else {
//                    clPhonetic.visibility = View.VISIBLE
//                }
//            })
            etWord.text.clear()

        }
//        vm.errorRightResponse.observe(this, Observer {
//            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
//        })
//        vm.errorRightWord.observe(this, Observer {
//            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
//        })
//
//        clPhonetic.setOnClickListener {
////            vm.soundPhonetic()
//        }
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


