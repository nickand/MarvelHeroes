package com.example.marvelheroes.data.repository

import com.example.marvelheroes.data.model.Character
import com.example.marvelheroes.data.model.CharacterDTOMapper
import com.example.marvelheroes.data.model.Resource
import com.example.marvelheroes.data.network.MarvelAPI

/**
 * Implements @see MercadoLibreAPI
 */
class CharactersRepositoryImpl(
    private val marvelAPI: MarvelAPI,
    //private val characterDAO: ProductDAO,
    private val dtoMapper: CharacterDTOMapper,
    //private val cacheMapper: ProductCacheMapper
) : CharactersRepository {

    /**
     * Will fetch results according to @param query, clear the database and then store the results
     * as the new cache data.
     */
    /*override suspend fun search(query: String): Resource<List<Product?>> {
        return try {
            val dtoList = mercadoLibreAPI.search(query).results
            val domainList = dtoMapper.toDomainList(dtoList)
            val cacheList = cacheMapper.fromList(domainList)
            productDAO.deleteAll()
            productDAO.save(*cacheList.toTypedArray())
            Resource.Success(domainList)
        } catch (throwable: Throwable) {
            Resource.Error(throwable.message ?: "Error fetching result list from service")
        }
    }*/

    override suspend fun getCharacters(): Resource<List<Character?>> {
        return try {
            val dtoList = marvelAPI.getCharacters().results
            val domainList = dtoMapper.toDomainList(dtoList)
            //val cacheList = cacheMapper.fromList(domainList)
            //productDAO.deleteAll()
            //productDAO.save(*cacheList.toTypedArray())
            Resource.Success(domainList)
        } catch (throwable: Throwable) {
            Resource.Error(throwable.message ?: "Error fetching result list from service")
        }
    }

    /**
     * Will get a Product details from the database cache, in case the data is not complete, the rest
     * of the information will be fetched from the service.
     */
   /* override suspend fun get(productId: String): Resource<Product> {
        return try {
            var productCache = productDAO.loadResult(productId)
            if (productCache.lastUpdated == null) {
                val productDTO = mercadoLibreAPI.getDetails(productId)
                val product = dtoMapper.mapToModel(productDTO)
                productCache = cacheMapper.mapFromModel(product)
                productDAO.update(productCache)
                Resource.Success(product)
            } else {
                Resource.Success(cacheMapper.mapToModel(productCache))
            }
        } catch (throwable: Throwable) {
            Resource.Error(throwable.message ?: "Error fetching product from database")
        }
    }*/
}