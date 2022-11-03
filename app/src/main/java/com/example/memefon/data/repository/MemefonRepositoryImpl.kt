package com.example.memefon.data.repository

import android.content.Context
import android.util.Log
import com.example.memefon.data.model.*
import com.example.memefon.data.network.MemefonAPI
import com.example.memefon.domain.MemefonModel

/**
 * Implements @see MarvelAPI
 */
class MemefonRepositoryImpl(
    private val memefonAPI: MemefonAPI,
    //private val characterDAO: ProductDAO,
    private val dtoMapper: MemefonDTOMapper,
    private val context: Context,
    //private val cacheMapper: ProductCacheMapper
) : MemefonRepository {

    override suspend fun getMemefonData(): Resource<List<MemefonModel>> {
        return try {
            val dtoList = memefonAPI.getMemefonData(context)
            Log.i("data", "DTO LIST: $dtoList")
            val domainList = dtoMapper.toDomainList(dtoList)
            Resource.Success(domainList)
        } catch (throwable: Throwable) {
            Resource.Error(throwable.message ?: "Error fetching result list from service")
        }
    }

    override suspend fun get(query: String): Resource<MemefonModel> {
        val char = getMemefonData().data?.find { it?.id.toString() == query }!!
        return Resource.Success(char)
    }

}
