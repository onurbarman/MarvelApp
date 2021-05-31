package com.app.marvelapp.data.repository

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.app.marvelapp.data.model.MarvelCharactersResults
import com.app.marvelapp.utils.Constants
import com.app.marvelapp.utils.Utils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*
import kotlin.coroutines.CoroutineContext

class MarvelDataSource(coroutineContext: CoroutineContext,
private val repository: MarvelRepository) :PageKeyedDataSource<String, MarvelCharactersResults>() {

    private val job = Job()
    private val scope = CoroutineScope(coroutineContext + job)
    val ts = (Calendar.getInstance(TimeZone.getTimeZone("UTC")).timeInMillis / 1000L).toString()
    val hash = Utils.md5(ts+ Constants.API_PRIVATE_KEY+ Constants.API_PUBLIC_KEY)

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, MarvelCharactersResults>) {
        scope.launch {
            val response = repository.getCharacters(ts = ts, hash = hash, offset = 0)
            response.data?.let { responseData ->
                Log.d("marvelpage","page: "+"1"+",loadsize: "+params.requestedLoadSize+
                        ",total: "+responseData.data.results.size)
                callback.onResult(responseData.data.results, null, (Constants.FIRST_PAGE+1).toString())
            }

        }

    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, MarvelCharactersResults>) {
        scope.launch {
                val page = params.key
                val numberOfItems = params.requestedLoadSize * page.toInt()
                val response = repository.getCharacters(ts = ts, hash = hash, offset = numberOfItems)
                response.data?.let { responseData ->
                    Log.d("marvelpage","page: "+page+",loadsize: "+numberOfItems+
                            ",total: "+responseData.data.results.size)
                    callback.onResult(responseData.data.results, (page.toInt()+1).toString())
                }
        }

    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, MarvelCharactersResults>) {
        scope.launch {
            val page = params.key
            val numberOfItems = params.requestedLoadSize * page.toInt()
            val response = repository.getCharacters(ts = ts, hash = hash, offset = numberOfItems)
            response.data?.let { responseData ->
                Log.d("marvelpage","page: "+page+",loadsize: "+numberOfItems+
                        ",total: "+responseData.data.results.size)
                callback.onResult(responseData.data.results, (page.toInt()-1).toString())
            }
        }

    }

    override fun invalidate() {
        super.invalidate()
        job.cancel()
    }

}