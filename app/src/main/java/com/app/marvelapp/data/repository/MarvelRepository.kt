package com.app.marvelapp.data.repository

import com.app.marvelapp.data.model.MarvelCharactersModel
import com.app.marvelapp.data.remote.Resource
import com.app.marvelapp.data.remote.ServiceClientInstance
import com.app.marvelapp.utils.Utils.safeApiCall

class MarvelRepository {
    suspend fun getCharacters(ts: String, hash: String, offset: Int): Resource<MarvelCharactersModel> {
        return safeApiCall(call = { ServiceClientInstance.getInstance().api.getCharacters(
            ts = ts, hash = hash, offset = offset
        ) })
    }

}