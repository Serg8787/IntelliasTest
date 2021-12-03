package com.tsybulnik.intelliastest

import com.tsybulnik.intelliastest.domain.entities.Meaning
import com.tsybulnik.intelliastest.domain.entities.Word
import com.tsybulnik.intelliastest.domain.entities.WordItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface Api {

    @GET("api/v2/entries/en/{wordEng}")
    fun getDataFromWord(@Path("wordEng") wordEng:String): Call<List<WordItem>>


}