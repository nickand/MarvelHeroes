package com.example.memefon.data.repository

import com.example.memefon.data.model.Resource
import com.example.memefon.domain.MemefonModel

/**
 * Data access functions
 */
interface MemefonRepository {
    suspend fun get(query: String) : Resource<MemefonModel>
    suspend fun getMemefonData() : Resource<List<MemefonModel>>
}