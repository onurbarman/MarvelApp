package com.app.marvelapp.data.repository

import com.app.marvelapp.data.model.characters.MarvelCharactersModel
import com.app.marvelapp.data.model.comics.ComicsModel
import com.app.marvelapp.data.remote.Resource
import com.app.marvelapp.data.remote.ServiceClientInstance
import com.app.marvelapp.utils.Constants
import com.app.marvelapp.utils.Utils
import com.app.marvelapp.utils.Utils.safeApiCall
import java.util.*

class MarvelRepository {
    val ts = (Calendar.getInstance(TimeZone.getTimeZone("UTC")).timeInMillis / 1000L).toString()
    val hash = Utils.md5(ts+ Constants.API_PRIVATE_KEY+ Constants.API_PUBLIC_KEY)

    suspend fun getCharacters(offset: Int): Resource<MarvelCharactersModel> {
        return safeApiCall(call = { ServiceClientInstance.getInstance().api.getCharacters(
            ts = ts, hash = hash, offset = offset
        ) })
    }

    suspend fun getComics(characterId: Int, dateRange: String): Resource<ComicsModel> {
        return safeApiCall(call = { ServiceClientInstance.getInstance().api.getComics(
            ts = ts, hash = hash, characterId = characterId, dateRange = dateRange
        ) })
    }

}