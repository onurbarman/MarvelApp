package com.app.marvelapp.data.model

data class MarvelCharactersData(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<MarvelCharactersResults>,
    val total: Int
)