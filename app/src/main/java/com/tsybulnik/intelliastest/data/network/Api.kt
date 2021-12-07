package com.tsybulnik.intelliastest.data.network

import androidx.lifecycle.LiveData
import com.tsybulnik.intelliastest.data.model.WordItemDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface Api {
    @GET("api/v2/entries/en/{wordEng}")
   suspend fun getDataFromWord(@Path("wordEng") wordEng:String): List<WordItemDto>
}