package com.example.marvelheroes.data.repository

import com.example.marvelheroes.data.model.Character
import com.example.marvelheroes.data.model.CharacterDTOMapper
import com.example.marvelheroes.data.model.Resource
import com.example.marvelheroes.data.network.MarvelAPI

/**
 * Implements @see MarvelAPI
 */
class CharactersRepositoryImpl(
    private val marvelAPI: MarvelAPI,
    //private val characterDAO: ProductDAO,
    private val dtoMapper: CharacterDTOMapper,
    //private val cacheMapper: ProductCacheMapper
) : CharactersRepository {

    override suspend fun getCharacters(): Resource<List<Character?>> {
        return try {
            val dtoList = marvelAPI.getCharacters().data?.results
            val domainList = dtoMapper.toDomainList(dtoList.orEmpty().filterNotNull())
            //val cacheList = cacheMapper.fromList(domainList)
            //productDAO.deleteAll()
            //productDAO.save(*cacheList.toTypedArray())
            Resource.Success(domainList)
        } catch (throwable: Throwable) {
            Resource.Error(throwable.message ?: "Error fetching result list from service")
        }
    }


    override suspend fun get(query: String): Resource<Character> {
        val char = getCharacters().data?.find { it?.id.toString() == query }!!
        return Resource.Success(char)
    }

}
