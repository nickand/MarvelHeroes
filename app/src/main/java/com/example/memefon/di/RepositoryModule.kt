package com.example.memefon.di

import android.content.Context
import com.example.memefon.data.model.Memefon
import com.example.memefon.data.model.MemefonDTOMapper
import com.example.memefon.data.network.MemefonAPI
import com.example.memefon.data.repository.MemefonRepository
import com.example.memefon.data.repository.MemefonRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMemefonRepository(
        dtoMapper: MemefonDTOMapper,
        @ApplicationContext context: Context
    ): MemefonRepository {
        return MemefonRepositoryImpl(
            memefonAPI = object : MemefonAPI {
                override suspend fun getMemefonData(context: Context): Memefon {
                    return super.getMemefonData(context)
                }
            },
            dtoMapper = dtoMapper,
            context = context
        )
    }
}
