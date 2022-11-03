package com.example.marvelheroes.data.network

import com.example.marvelheroes.data.model.CharacterListDTO
import retrofit2.http.GET

/**
 * Entry point for Marvel API
 */
interface MarvelAPI {

   @GET("v1/public/characters?ts=1655138129&apikey=8f6f5e5729414b00579c45287647d111&hash=2f9d478f882b4f2f9113fe7911c4b73b")
    suspend fun getCharacters(): CharacterListDTO
}