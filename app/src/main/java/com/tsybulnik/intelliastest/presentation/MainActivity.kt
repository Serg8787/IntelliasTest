package com.tsybulnik.intelliastest.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tsybulnik.intelliastest.databinding.ActivityMainBinding
import com.tsybulnik.intelliastest.presentation.adapters.WordAdapter


class MainActivity : AppCompatActivity() {
    private lateinit var vm: MainViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        vm = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.btSearch.setOnClickListener {
            hideKeybord()
            val word: String = binding.etEnterWord.text.toString()
            vm.getData(word)
            vm.word.observe(this, {
                binding.tvMainWord.text = it.word
                binding.tvPhonetic.text = "[ ${it.phonetics.first().text} ]"
                binding.tvOrigin.text = it.origin
                val size: Int = it.meanings.size
                val arraydefinition = arrayOfNulls<String>(size)
                val arrayPartOfSpeech = arrayOfNulls<String>(size)
                for (i in 0 until size) {
                    arrayPartOfSpeech[i] = it.meanings[i].partOfSpeech
                    arraydefinition[i] = it.meanings[i].definitions.first().definition
                }
                val map = arrayPartOfSpeech.zip(arraydefinition).toMap()
                binding.recycler.adapter = WordAdapter(map)
            })
            binding.etEnterWord.text.clear()
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


