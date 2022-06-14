package com.example.marvelheroes.data.repository

import com.example.marvelheroes.data.model.Character
import com.example.marvelheroes.data.model.Resource

/**
 * Data access functions
 */
interface CharactersRepository {
    //suspend fun search(query: String) : Resource<List<Character?>>
    suspend fun getCharacters() : Resource<List<Character?>>
    //suspend fun get(query: String) : Resource<Character>
}