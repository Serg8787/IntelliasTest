package com.tsybulnik.intelliastest.data.network

import com.tsybulnik.intelliastest.data.model.WordItem
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface Api {
    @GET("api/v2/entries/en/{wordEng}")
   fun getDataFromWord(@Path("wordEng") wordEng:String): Call<List<WordItem>>
}