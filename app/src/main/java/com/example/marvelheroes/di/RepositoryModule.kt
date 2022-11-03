package com.example.marvelheroes.di

import com.example.marvelheroes.data.model.CharacterDTOMapper
import com.example.marvelheroes.data.network.MarvelAPI
import com.example.marvelheroes.data.repository.CharactersRepository
import com.example.marvelheroes.data.repository.CharactersRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRecipeRepository(
        api: MarvelAPI,
        dtoMapper: CharacterDTOMapper,
        //productDAO: ProductDAO,
        //cacheMapper: ProductCacheMapper
    ): CharactersRepository {
        return CharactersRepositoryImpl(
            marvelAPI = api,
            dtoMapper = dtoMapper,
            //productDAO = productDAO,
            //cacheMapper = cacheMapper
        )
    }
}
