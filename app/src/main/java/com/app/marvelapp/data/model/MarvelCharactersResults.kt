package com.app.marvelapp.data.model

data class MarvelCharactersResults(
    val description: String,
    val id: Int,
    val name: String,
    val thumbnail: MarvelCharactersThumbnail
)