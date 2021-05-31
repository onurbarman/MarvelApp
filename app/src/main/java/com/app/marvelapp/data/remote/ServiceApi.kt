package com.app.marvelapp.data.remote

import com.app.marvelapp.data.model.MarvelCharactersModel
import com.app.marvelapp.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApi {

    //Get All Characters
    @GET("characters")
    suspend fun getCharacters(
        @Query("apikey") apiKey: String = Constants.API_PUBLIC_KEY,
        @Query("ts") ts: String,
        @Query("hash") hash: String,
        @Query("limit") limit: Int = Constants.PAGE_SIZE,
        @Query("offset") offset: Int? = 0): Response<MarvelCharactersModel>


}